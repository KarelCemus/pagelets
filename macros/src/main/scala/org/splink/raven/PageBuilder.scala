package org.splink.raven

import akka.stream.Materializer
import play.api.mvc.{AnyContent, Request}

import scala.concurrent.{ExecutionContext, Future}

trait PageBuilder {
  def builder: PageBuilderService

  trait PageBuilderService {
    def build(pagelet: Pagelet, args: Arg*)(implicit ec: ExecutionContext, r: Request[AnyContent], m: Materializer): Future[PageletResult]
  }
}

trait PageBuilderImpl extends PageBuilder {
  self: LeafBuilder =>

  override val builder = new PageBuilderService {
    val log = play.api.Logger(getClass.getSimpleName).logger

    override def build(p: Pagelet, args: Arg*)(
      implicit ec: ExecutionContext, r: Request[AnyContent], m: Materializer) = {
      val requestId = RequestId.create

      def rec(p: Pagelet): Future[PageletResult] =
        p match {
          case t@Tree(id, children) =>
            val start = System.currentTimeMillis()
            log.info(s"$requestId Invoke pagelet ${p.id}")

            Future.sequence(children.map(rec)).map(t.combine).map { result =>
              log.info(s"$requestId Finish pagelet ${p.id} took ${System.currentTimeMillis() - start}ms")
              result
            }

          case l: Leaf[_, _] =>
            val isRoot = p.id == l.id
            leafBuilderService.build(l, args, requestId, isRoot)
        }

      rec(p)
    }
  }

}