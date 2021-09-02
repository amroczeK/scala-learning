import squants.energy.JoulesPerCubicMeter

import squants.time.Minutes
import squants._
import squants.motion.VelocityConversions._
import squants.motion.Velocity._
import squants.motion.{MetersPerSecond}
import squants.thermal.Celsius
import squants.energy.PowerConversions._
import squants.time.TimeConversions._
import squants.electro.ElectricPotential._
import squants.electro.ElectricPotentialConversions._
import squants.electro.{Volts}
import squants.motion.{Pascals, Bars, KilogramsPerSecond, PoundsPerSquareInch}
import squants.motion.MassFlowConversions._
import squants.motion.PressureConversions._



val power3: Power = 2.0.milliwatts
val power4: Power = 2.0.megawatts

val days: squants.time.Time = 5.days

power3 + power4

days + days

power3 * 2 

power3 * 2 + 10.megawatts

power3.map(_ * 2 + 10)

power3 * 2 + (10 * 1000).kilowatts


val test: Double = 1.5.volts.toMillivolts

val test2: Double = (10*1000).pascals.toBars

val test3: Double = 10.0.psi to Pascals

