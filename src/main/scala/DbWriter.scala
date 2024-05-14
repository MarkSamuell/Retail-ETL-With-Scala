import RuleEngine.Order
import java.sql.{Connection, Date, DriverManager, PreparedStatement, Timestamp}
import scala.language.postfixOps


object DbWriter {

    def write(ordersList: List[Order]): Unit = {
        val url = "jdbc:oracle:thin:@//localhost:1521/XE"
        val username = "ATTR"
        val password = "123"

        val insertStatement = {
            """
                INSERT INTO orders (timestamp, productName, expiryDate,
                                    quantity, unitPrice, channel,
                                    paymentMethod, discount, finalPrice)
                VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)
                """.stripMargin
        }

       // try {
            Class.forName("oracle.jdbc.driver.OracleDriver") // Load the Oracle JDBC driver
            val connection: Connection = DriverManager.getConnection(url, username, password)
            // log_event(writer, f, "Debug", "Successfully Opened database connection")
            // Prepare the INSERT statement
            val preparedStatement: PreparedStatement = connection.prepareStatement(insertStatement: String)

            // Insert data into the table
            ordersList.foreach { order =>
                preparedStatement.setString(1, order.timestamp)
                preparedStatement.setString(2, order.productName)
                preparedStatement.setDate(3, new java.sql.Date(order.expiryDate.getTime)) // Convert java.util.Date to java.sql.Date
                preparedStatement.setInt(4, order.quantity)
                preparedStatement.setDouble(5, order.unitPrice)
                preparedStatement.setString(6, order.channel)
                preparedStatement.setString(7, order.paymentMethod)
                preparedStatement.setDouble(8, order.discount)
                preparedStatement.setDouble(9, order.finalPrice)

                preparedStatement.addBatch() // Add the current INSERT statement to the batch
            }

            //Execute the batch of INSERT statements
            preparedStatement.executeBatch()
//        } catch {
//            case e: Exception =>
//                log_event(writer, f, "Error", s"Failed to close preparedStatement: ${e.getMessage}")
//        } finally {
//            // Close resources
//            if (preparedStatement != null) preparedStatement.close()
//            if (connection != null) connection.close()
//            log_event(writer, f, "Info", "Successfully inserted into database")
//            log_event(writer, f, "Debug", "Closed database connection")
//        }
   // }

    }
}
