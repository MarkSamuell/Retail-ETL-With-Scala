import java.util.Date
import scala.io.Source
import java.text.SimpleDateFormat


object RuleEngine extends App {

    // Define case class Order
    case class Order(timestamp: String,
                     productName: String,
                     expiryDate: Date,
                     quantity: Int,
                     unitPrice: Double,
                     channel: String,
                     paymentMethod: String,
                     discount: Double,
                     finalPrice: Double)

    // Date format for parsing dates
    val dateFormat = new SimpleDateFormat("yyyy-MM-dd")

    // Read lines from the CSV file
    val lines = Source.fromFile("./src/main/resources/TRX1000.csv").getLines().toList.tail

    // Convert each line to an Order object
    val ordersList = lines.map { line =>
        val Array(timestamp, productName, expiryDate, quantityStr, unitPriceStr, channel, paymentMethod) = line.split(",")
        val quantity = quantityStr.toInt
        val unitPrice = unitPriceStr.toDouble
        val timestampParsed = timestamp
        val expiryDateParsed = dateFormat.parse(expiryDate) // Parse the expiryDate string to Date
        Order(timestampParsed, productName, expiryDateParsed, quantity, unitPrice, channel, paymentMethod, 0.0, quantity*unitPrice)
    }

    // function that returns list of tuples of rules [(QUALIFYING RULES, CALCULATION RULE)]
    /*
    if you want to add a new rule, kindly add your new rules here :)
     */
    def getDiscountRules(): List[(Order => Boolean, Order => Double)] = {
        List(
            ((order: Order) => Rules.isAQualified(order), (order: Order) => Rules.A(order)),
                ((order: Order) => Rules.isBQualified(order), (order: Order) => Rules.B(order)),
                ((order: Order) => Rules.isCQualified(order), (order: Order) => Rules.C(order)),
                ((order: Order) => Rules.isDQualified(order), (order: Order) => Rules.D(order)),
                ((order: Order) => Rules.isEQualified(order), (order: Order) => Rules.E(order))
        )
    }

    // function that takes order and apply list of rules on it and return order with discount
    def getOrderWithDiscount(order: Order, rules: List[(Order => Boolean, Order => Double)]): Order = {
        val discount = rules.filter(_._1(order)).map(_._2(order)).sorted(Ordering[Double]).reverse
        println(discount)
        val averageDiscount: Double = {
            if (discount.length > 1) { discount.take(2).sum / 2 }
            else if (discount.length == 1) discount(0)
            else 0.0
        }

        val finalPrice: Double = order.quantity * order.unitPrice * (1 - averageDiscount) // Calculate final price with discount

        val new_order: Order = order.copy(discount = averageDiscount, finalPrice = finalPrice) // Set discount and finalPrice fields
        new_order
    }

    ordersList.take(10).map(x => getOrderWithDiscount(x, getDiscountRules())).foreach(println)
}