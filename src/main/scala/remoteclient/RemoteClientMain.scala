package remoteclient

import actors.RemoteNodeActor
import akka.actor.{ActorRef, ActorSystem, AddressFromURIString, Deploy}
import akka.remote.RemoteScope
import com.typesafe.config.{ConfigParseOptions, ConfigFactory}
import domain.Node

object RemoteClientMain {

  /**
   * First arg is name, rest are depends
   */
  def main(args: Array[String]): Unit = {
    require(args.length > 0, "Command line usage: name <dependencies*>")
    val node = Node(args(0), args.drop(1))

    val actorSystem = ActorSystem(s"Client${node.id}")

    val actorRef: ActorRef = actorSystem.actorOf(RemoteNodeActor.props(node), "remoteNodeActor")

    println(s"Added Node $node")

    actorRef ! "Hi"

    actorSystem.awaitTermination()
  }
}
