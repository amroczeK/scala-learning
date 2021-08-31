package UnitConversion

import squants.electro.{Volts, Millivolts}
import squants.electro.ElectricPotential._
import squants.electro.ElectricPotentialConversions._
import squants.electro.ElectricPotentialUnit
import squants.space.{Millimeters, Centimeters, Meters, Inches, Feet, Yards}
import squants.space.LengthConversions._
import squants.motion.{Pascals, Bars, MillimetersOfMercury, PoundsPerSquareInch, MetersPerSecond, KilometersPerHour, UsMilesPerHour, InternationalMilesPerHour, FeetPerSecond, Knots}
import squants.motion.PressureConversions._
import squants.motion.Velocity._
import squants.thermal.{Celsius, Fahrenheit}
import squants.thermal.TemperatureConversions._
import squants.time.{Seconds, Minutes, Hours}

sealed trait Quantity
case object Pressure extends Quantity {
    val symbol: String = Pascals.symbol
    def convertToSiUnit(unitSymbol: String, value: Double): Double = unitSymbol match {
        case Pascals.symbol => value
        case "kPa" => (value*1000)
        case "mbar" => Bars(value/1000) to Pascals // Converts mbar to bar then Pa
        case "cbar" => Bars(value*0.01) to Pascals
        case Bars.symbol => Bars(value) to Pascals
        case "hPa" => (value*100)
        case MillimetersOfMercury.symbol => MillimetersOfMercury(value) to Pascals
        case PoundsPerSquareInch.symbol => PoundsPerSquareInch(value) to Pascals
    }
}
case object ElectricPotential extends Quantity {
    val symbol: String = Volts.symbol
    def convertToSiUnit(unitSymbol: String, value: Double): Double = unitSymbol match {
        case Volts.symbol => value
        case Millivolts.symbol => Millivolts(value) to Volts
    }
}
case object Precipitation extends Quantity {
    val symbol: String = Millimeters.symbol
    def convertToSiUnit(unitSymbol: String, value: Double): Double = unitSymbol match {
        case Millimeters.symbol => value
        case Centimeters.symbol => Centimeters(value) to Millimeters
        case Meters.symbol => Meters(value) to Millimeters
        case Inches.symbol => Inches(value) to Millimeters
        case Feet.symbol => Feet(value) to Millimeters
        case Yards.symbol => Yards(value) to Millimeters
    }
}
case object TemperatureScale extends Quantity {
    val symbol: String = Celsius.symbol
    def convertToSiUnit(unitSymbol: String, value: Double): Double = unitSymbol match {
        case Celsius.symbol => value
        case Fahrenheit.symbol => Fahrenheit(value) to Celsius
    }
}
case object Velocity extends Quantity {
    val symbol: String = MetersPerSecond.symbol
    def convertToSiUnit(unitSymbol: String, value: Double): Double = unitSymbol match {
        case MetersPerSecond.symbol => value
        case KilometersPerHour.symbol => KilometersPerHour(value) to MetersPerSecond
        case UsMilesPerHour.symbol => UsMilesPerHour(value) to MetersPerSecond
        case FeetPerSecond.symbol => FeetPerSecond(value) to MetersPerSecond
        case "ft/min" => FeetPerSecond(value / 60) to MetersPerSecond // Converts ft/min to ft/s then m/s
        case "yd/min" => FeetPerSecond(value / 20) to MetersPerSecond // Converts yd/min to ft/s then m/s
        case Knots.symbol => Knots(value) to MetersPerSecond
    }
}
case object Time extends Quantity {
    val symbol: String = "sec"
    def convertToSiUnit(unitSymbol: String, value: Double): Double = unitSymbol match {
        case Seconds.symbol => value
        case "min" => Seconds(value) to Seconds
        case Hours.symbol => Hours(value) to Seconds
    }
}


class Sensor(sensorName: String, var unitSymbol: String, var value: Double){
    private val _quantity: Quantity = setQuantity(sensorName)
    private val _sensorName: String = sensorName
    private val _unitSymbol: String = setUnitSymbol(_quantity)
    private val _value: Double = setSetValue(_quantity, unitSymbol, value)
    

    def setQuantity(sensorName: String): Quantity = sensorName match {
        case s if s.equalsIgnoreCase("Air Pressure") => Pressure
        case s if s.equalsIgnoreCase("VPD") => Pressure
        case s if s.equalsIgnoreCase("Battery") => ElectricPotential
        case s if s.equalsIgnoreCase("Solar panel") => ElectricPotential
        case s if s.equalsIgnoreCase("Percipitation") => Precipitation
        case s if s.equalsIgnoreCase("HC Air temperature") => TemperatureScale
        case s if s.equalsIgnoreCase("DeltaT") => TemperatureScale
        case s if s.equalsIgnoreCase("Dew Point") => TemperatureScale
        case s if s.equalsIgnoreCase("U-sonic wind dir") => Precipitation
        case s if s.equalsIgnoreCase("Solar radiation") => Precipitation
        case s if s.equalsIgnoreCase("Wind gust") => Velocity
        case s if s.equalsIgnoreCase("U-sonic wind speed") => Velocity
        case s if s.equalsIgnoreCase("Leaf Wetness") => Time
    }

    def getQuantity: Quantity = _quantity
    def getSensorName: String = _sensorName
    def getUnitSymbol: String = _unitSymbol
    def getValue: Double = _value

    def setUnitSymbol(quantity: Quantity): String = quantity match {
        case Pressure => Pressure.symbol
        case ElectricPotential => ElectricPotential.symbol
        case Precipitation => Precipitation.symbol
        case TemperatureScale => TemperatureScale.symbol
        case Velocity => Velocity.symbol
        case Time => Time.symbol
    }

    def setSetValue(quantity: Quantity, unitSymbol: String, value: Double): Double = quantity match {
        case Pressure => Pressure.convertToSiUnit(unitSymbol, value)
        case ElectricPotential => ElectricPotential.convertToSiUnit(unitSymbol, value)
        case Precipitation => Precipitation.convertToSiUnit(unitSymbol, value)
        case TemperatureScale => TemperatureScale.convertToSiUnit(unitSymbol, value)
        case Velocity => Velocity.convertToSiUnit(unitSymbol, value)
        case Time => Time.convertToSiUnit(unitSymbol, value)
    }

    def convertValue(): Double = 10.0

    override def toString: String = s"Sensor name: ${_sensorName}, Quantity: ${_quantity}, Unit symbol: ${_unitSymbol}, Value: ${_value}"
}

object TransformerRunner extends App {
    val s1 = new Sensor("Air pressure", "mbar", 10.0)
    val s2 = new Sensor("Battery", "mV", 5.0)
    val s3 = new Sensor("Percipitation", "yd", 10.0)
    val s4 = new Sensor("Solar Panel", "mV", 7.0)
    val s5 = new Sensor("Air pressure", "mbar", 3.0)
    val s6 = new Sensor("Air pressure", "bar", 10.0)
    val s7 = new Sensor("Air pressure", "mmHg", 10.0)
    val s8 = new Sensor("Air pressure", "psi", 10.0)
    val s9 = new Sensor("HC Air temperature", "°F", 10.0)
    val s10 = new Sensor("U-sonic wind speed", "ft/min", 15.0)
    val s11 = new Sensor("U-sonic wind speed", "kn", 15.0)
    val s12 = new Sensor("U-sonic wind speed", "yd/min", 15.0)
    val s13 = new Sensor("Leaf wetness", "h", 15.0)

    println(s1.toString)
    println(s2.toString)
    println(s3.toString)
    println(s4.toString)
    println(s5.toString)
    println(s6.toString)
    println(s7.toString)
    println(s8.toString)
    println(s9.toString)
    println(s10.toString)
    println(s11.toString)
    println(s12.toString)
    println(s13.toString)
}