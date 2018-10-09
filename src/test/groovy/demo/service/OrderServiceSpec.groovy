package demo.service

import demo.domain.Order
import spock.lang.Specification
import spock.lang.Unroll

class OrderServiceSpec extends Specification {
    @Unroll
    def "Testing PlaceOrder() mocking example"() {
        given: "Create order instance"
        Order order = new Order(quantity,itemName,price)
        OrderService orderService = OrderService.getInstance()
        and: "mocking EmailService"
        EmailService emailService = Mock(EmailService)
        orderService.emailService = emailService
        when:
        Boolean status = orderService.placeOrder(order)
        then:
        1 * emailService.sendEmail(order)
        order.getPriceWithTex() == expected
        where: "Checking on different values"
        quantity | itemName       | price | expected
        2        | "Product Name" | 2400  | 480d
        3        | "Product1"     | 3000  | 600d
        0        | null           | 0     | 0d
    }
    @Unroll("Testing PlaceOrder() stubing example: #sno")
    def "Testing PlaceOrder() stubing example"() {
        given: "Create order instance"
        Order order = new Order()
        OrderService orderService = OrderService.getInstance()
        String cc = "dhanendrakumarkht@gmail.com"

        and: "Initializing order"
        order.setItemName("Product Name")
                .setQuantity(2)
                .setPrice(2400)

        and: "mocking EmailService"
        EmailService emailService = Mock(EmailService)
        orderService.emailService = emailService

        when:
        boolean status = orderService.placeOrder(order, cc)

        then:
        1 * emailService.sendEmail(order, cc) >> inputStatus
        order.getPriceWithTex() == 480d
        status == expectedStatus

        where:
        sno | inputStatus | expectedStatus
        1   | true        | true
        2   | false       | false
    }

}