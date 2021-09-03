import org.scalatest.funsuite.AnyFunSuite
import UnitConversion.Sensor
import UnitConversion.{Pressure, ElectricPotential, Precipitation, TemperatureScale, Velocity, Time}

class TransformerSpec extends AnyFunSuite {

    val p1 = new Sensor("Air pressure", "Pa", 10.0);
    val p2 = new Sensor("VPD", "kPa", 10.0);
    val p3 = new Sensor("Air pressure", "mbar", 10.0);
    val p4 = new Sensor("VPD", "cbar", 10.0);
    val p5 = new Sensor("Air pressure", "bar", 10.0);
    val p6 = new Sensor("VPD", "hPa", 10.0);
    val p7 = new Sensor("Air pressure", "mmHg", 10.0);
    val p8 = new Sensor("Air pressure", "psi", 10.0);

    test("All sensors with Pressure quantity convert unit and value to SI base unit."){
        assert(p1.getUnitSymbol === "Pa" && p1.getValue === 10.0 && p1.getQuantity === Pressure)
        assert(p2.getUnitSymbol === "Pa" && p2.getValue === 10000.0 && p2.getQuantity === Pressure)
        assert(p3.getUnitSymbol === "Pa" && p3.getValue === 1000.0 && p3.getQuantity === Pressure)
        assert(p4.getUnitSymbol === "Pa" && p4.getValue === 10000.0 && p4.getQuantity === Pressure)
        assert(p5.getUnitSymbol === "Pa" && p5.getValue === 1000000.0 && p5.getQuantity === Pressure)
        assert(p6.getUnitSymbol === "Pa" && p6.getValue === 1000.0 && p6.getQuantity === Pressure)
        assert(p7.getUnitSymbol === "Pa" && p7.getValue === 1333.22387415 && p7.getQuantity === Pressure)
        assert(p8.getUnitSymbol === "Pa" && p8.getValue === 68947.57293168361 && p8.getQuantity === Pressure)
    }

    val e1 = new Sensor("Battery", "mV", 10.0)
    val e2 = new Sensor("Solar Panel", "V", 10.0)

    test("All sensors with ElectricPotential quantity convert unit and value to SI base unit."){
        assert(e1.getUnitSymbol === "V" && e1.getValue === 0.01 && e1.getQuantity === ElectricPotential)
        assert(e2.getUnitSymbol === "V" && e2.getValue === 10.0 && e2.getQuantity === ElectricPotential)
    }

    val pre1 = new Sensor("Precipitation", "mm", 10.0)
    val pre2 = new Sensor("Precipitation", "cm", 10.0)
    val pre3 = new Sensor("Precipitation", "m", 10.0)
    val pre4 = new Sensor("Precipitation", "in", 10.0)
    val pre5 = new Sensor("Precipitation", "ft", 10.0)
    val pre6 = new Sensor("Precipitation", "yd", 10.0)

    test("All sensors with Precipitation quantity convert unit and value to SI base unit."){
        assert(pre1.getUnitSymbol === "mm" && pre1.getValue === 10.0 && pre1.getQuantity === Precipitation)
        assert(pre2.getUnitSymbol === "mm" && pre2.getValue === 100.0 && pre2.getQuantity === Precipitation)
        assert(pre3.getUnitSymbol === "mm" && pre3.getValue === 10000.0 && pre3.getQuantity === Precipitation)
        assert(pre4.getUnitSymbol === "mm" && pre4.getValue === 254.000508 && pre4.getQuantity === Precipitation)
        assert(pre5.getUnitSymbol === "mm" && pre5.getValue === 3048.006096 && pre5.getQuantity === Precipitation)
        assert(pre6.getUnitSymbol === "mm" && pre6.getValue === 9144.018288 && pre6.getQuantity === Precipitation)
    }

    val temp1 = new Sensor("DeltaT", "°C", 10.0)
    val temp2 = new Sensor("Dew Point", "°F", 10.0)

    test("All sensors with TemperatureScale quantity convert unit and value to SI base unit."){
        assert(temp1.getUnitSymbol === "°C" && temp1.getValue === 10.0 && temp1.getQuantity === TemperatureScale)
        assert(temp2.getUnitSymbol === "°C" && temp2.getValue === -12.222222222222221 && temp2.getQuantity === TemperatureScale)
    }

    val vel1 = new Sensor("U-sonic wind speed", "m/s", 10.0)
    val vel2 = new Sensor("Wind gust", "km/h", 10.0)
    val vel3 = new Sensor("U-sonic wind speed", "mph", 10.0)
    val vel4 = new Sensor("Wind gust", "ft/s", 10.0)
    val vel5 = new Sensor("U-sonic wind speed", "ft/min", 10.0)
    val vel6 = new Sensor("Wind gust", "yd/min", 10.0)
    val vel7 = new Sensor("U-sonic wind speed", "kn", 10.0)

    test("All sensors with Velocity quantity convert unit and value to SI base unit."){
        assert(vel1.getUnitSymbol === "m/s" && vel1.getValue === 10.0 && vel1.getQuantity === Velocity)
        assert(vel2.getUnitSymbol === "m/s" && vel2.getValue === 2.7777777777777777 && vel2.getQuantity === Velocity)
        assert(vel3.getUnitSymbol === "m/s" && vel3.getValue === 4.4704089408 && vel3.getQuantity === Velocity)
        assert(vel4.getUnitSymbol === "m/s" && vel4.getValue === 3.048006096 && vel4.getQuantity === Velocity)
        assert(vel5.getUnitSymbol === "m/s" && vel5.getValue === 0.050800101599999994 && vel5.getQuantity === Velocity)
        assert(vel6.getUnitSymbol === "m/s" && vel6.getValue === 0.1524003048 && vel6.getQuantity === Velocity)
        assert(vel7.getUnitSymbol === "m/s" && vel7.getValue === 5.144444444444445 && vel7.getQuantity === Velocity)
    }

    val time1 = new Sensor("Leaf wetness", "sec", 10.0)
    val time2 = new Sensor("Leaf wetness", "min", 10.0)
    val time3 = new Sensor("Leaf wetness", "h", 10.0)

    test("All sensors with Time quantity convert unit and value to SI base unit."){
        assert(time1.getUnitSymbol === "sec" && time1.getValue === 10.0 && time1.getQuantity === Time)
        assert(time2.getUnitSymbol === "sec" && time2.getValue === 600.0 && time2.getQuantity === Time)
        assert(time3.getUnitSymbol === "sec" && time3.getValue === 36000.0 && time3.getQuantity === Time)
    }
}