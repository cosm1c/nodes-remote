package domain

import domain.Node.NodeId

@SerialVersionUID(1L)
case class Node(id: NodeId, depends: Seq[NodeId]) {
  def this(label: NodeId) = this(label, Seq())
}

object Node {

  type NodeId = String

  def apply(id: NodeId) = new Node(id)

}
