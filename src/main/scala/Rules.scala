import java.util.Date
import scala.io.Source
import java.util.concurrent.TimeUnit
import java.text.SimpleDateFormat
import RuleEngine.Order 



object Rules {

    // Date format for parsing dates
    val dateFormat = new SimpleDateFormat("yyyy-MM-dd") 
    
    // Rules definitions each rule has qulification function & calcualtion function

    // rule A
    def isAQualified(order: Order): Boolean = {
        val timestampDate = order.timestamp
        val expiryDate = order.expiryDate
        val difference = TimeUnit.DAYS.convert(expiryDate.getTime - dateFormat.parse(order.timestamp.substring(0, 10))
            .getTime, TimeUnit.MILLISECONDS)
        println("dff: " + difference + (difference < 30))
        difference < 30
    }

    def A(order: Order): Double = {
        val timestampDate = order.timestamp
        val expiryDate = order.expiryDate
        val difference = TimeUnit.DAYS.convert(expiryDate.getTime - dateFormat.parse(order.timestamp.substring(0, 10))
            .getTime, TimeUnit.MILLISECONDS).toDouble
        val discount = (30 - difference) / 100
        println(discount)
        discount
    }

    //    //rule B
    //    def isBQualified(order: Order): Boolean = ???
    //    def B(order: Order): Double = ???
    //
    //    //rule c
    //    def isCQualified(order: Order): Boolean = ???
    //    def C(order: Order): Double = ???


    

}
