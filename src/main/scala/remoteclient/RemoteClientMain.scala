package remoteclient

import actors.RemoteNodeActor
import akka.actor.{ActorRef, ActorSystem, AddressFromURIString, Deploy}
import akka.remote.RemoteScope
import com.typesafe.config.ConfigFactory
import domain.Node

object RemoteClientMain {

  /**
   * First arg is name, rest are depends
   */
  def main(args: Array[String]): Unit = {
    require(args.length > 0, "Usage: name <dependencies*>")
    val node = Node(args(0), args.drop(1))

    val actorSystem = ActorSystem(s"Client${node.id}", ConfigFactory.parseString(
      """
        |akka {
        |  actor {
        |    provider = "akka.remote.RemoteActorRefProvider"
        |  }
        |  deployment {
        |    "/WRONG/remoteNodeActor" {
        |      remote = "akka.tcp://application@127.0.0.1:2552"
        |    }
        |  }
        |  remote {
        |    enabled-transports = ["akka.remote.netty.tcp"]
        |    netty.tcp {
        |      hostname = "127.0.0.1"
        |      port = 0
        |    }
        |  }
        |}
      """.stripMargin))

    //val actorRef: ActorRef = actorSystem.actorOf(RemoteNodeActor.props(node), "remoteNodeActor")
    val actorRef: ActorRef = actorSystem.actorOf(RemoteNodeActor.props(node)
      .withDeploy(Deploy(scope = RemoteScope(AddressFromURIString("akka.tcp://application@127.0.0.1:2552")))),
      "remoteNodeActor")

    println(s"Added Node $node")

    actorRef ! "Hi"

    actorSystem.awaitTermination()
  }
}
