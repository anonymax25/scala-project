package fr.esgi.al.funprog

import fr.esgi.al.funprog._

@SuppressWarnings(Array("org.wartremover.warts.Var"))
case class Mower(
    var x: Int,
    var y: Int,
    var direction: Direction,
    var actions: List[Action]
) {}
