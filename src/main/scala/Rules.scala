import java.util.concurrent.TimeUnit
import java.text.SimpleDateFormat
import RuleEngine.Order


object Rules {

    // Date format for parsing dates
    val dateFormat = new SimpleDateFormat("yyyy-MM-dd")

    // Rules definitions each rule has qualification function & calculation function

    // rule A
    def isAQualified(order: Order): Boolean = {
        val timestampDate = order.timestamp
        val expiryDate = order.expiryDate
        val difference = TimeUnit.DAYS.convert(expiryDate.getTime - dateFormat.parse(order.timestamp.substring(0, 10))
            .getTime, TimeUnit.MILLISECONDS)
        difference < 30
    }

    def A(order: Order): Double = {
        val timestampDate = order.timestamp
        val expiryDate = order.expiryDate
        val difference = TimeUnit.DAYS.convert(expiryDate.getTime - dateFormat.parse(order.timestamp.substring(0, 10))
            .getTime, TimeUnit.MILLISECONDS).toDouble
        val discount = (30 - difference) / 100
        discount
    }

    //rule B
    def isBQualified(order: Order): Boolean = {
        order.productName.startsWith("Wine") || order.productName.startsWith("Cheese")
    }

    def B(order: Order): Double = {
        val discount = {
            if (order.productName.startsWith("Wine")) 0.05
            else if (order.productName.startsWith("Cheese")) 0.10
            else 0.0
        }
        discount
    }

    //rule c
    def isCQualified(order: Order): Boolean = {
        val dayOfMonth = order.timestamp.substring(8, 10).toInt // Extract day of the month from the timestamp
        val monthOfYear = order.timestamp.substring(5, 7).toInt // Extract month of the year from the timestamp
        dayOfMonth == 23 && monthOfYear == 3 // Check if the day is 23rd and the month is March (3)
    }
    def C(order: Order): Double = {
        0.50
    }


    //rule d
    def isDQualified(order: Order): Boolean = {
        order.channel == "App"
    }

    def D(order: Order): Double = {
        val discount = Math.ceil(order.quantity.toDouble / 5) * 5 // Round up quantity to the nearest multiple of 5
        discount / 100
    }

    //rule E
    def isEQualified(order: Order): Boolean = {
        order.paymentMethod == "Visa"
    }

    def E(order: Order): Double = {
        0.05
    }

}
