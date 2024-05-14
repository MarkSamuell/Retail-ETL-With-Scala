import RuleEngine.Order
import java.io.{File, FileOutputStream, PrintWriter}
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.sql.{Connection, Date, DriverManager, PreparedStatement}


object DbWriter {

    val logFilePath: String = "./src/main/resources/logs" // Relative path to the log file
    val logFile: File = new File(logFilePath)


    def write(ordersList: List[Order]): Unit = {
        val url = "jdbc:oracle:thin:@//localhost:1521/XE"
        val username = "ATTR" // schema name
        val password = "123" // schema password

        val insertStatement = {
            """
                INSERT INTO orders (timestamp, productName, expiryDate,
                                    quantity, unitPrice, channel,
                                    paymentMethod, discount, finalPrice)
                VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)
                """.stripMargin
        }

        try {
            Class.forName("oracle.jdbc.driver.OracleDriver") // Load the Oracle JDBC driver
            val connection: Connection = DriverManager.getConnection(url, username, password)
            // Success connect message
            logEvent(logFile, "Debug", "Successfully Opened database connection")
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
            // insert success message
            logEvent(logFile, "Info", "Batch Successfully Inserted in database")
        } catch {
            // log error
            case e: Exception =>
                logEvent(logFile, "Error", s"Failed to close preparedStatement: ${e.getMessage}")
        }

    }

    def logEvent(file: File, logType: String, message: String): Unit = {
        val timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))
        val logMessage = s"[$timestamp] [$logType] $message"

        // Check if the file exists, create it if it doesn't
        if (!file.exists()) {
            file.createNewFile()
        }

        val writer = new PrintWriter(new FileOutputStream(
            file,
            true /* append = true */))
        try {
            writer.println(logMessage)
        } finally {
            writer.close()
        }
    }
}
