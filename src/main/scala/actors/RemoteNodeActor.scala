package actors

import akka.actor.{Actor, ActorLogging, Props}
import domain.Node

class RemoteNodeActor(node: Node) extends Actor with ActorLogging {

  override def receive = {
    case data@_ => log.info(s"Received $data")
  }

}

object RemoteNodeActor {

  def props(node: Node) = Props(new RemoteNodeActor(node))

}