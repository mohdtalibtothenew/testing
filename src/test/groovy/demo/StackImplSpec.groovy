package demo

import spock.lang.Ignore
import spock.lang.Specification
import spock.lang.Unroll

class StackImplSpec extends Specification {

    def "Testing push() for success"() {
        setup:
        StackImpl stack = new StackImpl()
        when:
        stack.push("item1")
        then:
        stack.getStack().size() == 1     // When we push the items to the stack the size becomes 1
    }                                    // so it is result is equal to 1 and test case passes
    @Ignore
    def "testing push() for failure"() {
        setup:
        StackImpl stack = new StackImpl()
        when:
        stack.push("item1")
        then:
        stack.getStack().size() == 0   // If we have pushed the item into the stack the size should be zero.
    }
    @Unroll
    def "Testing pop() for single item"() {
        given: "initializing initial stack"
        StackImpl stack = new StackImpl();
        expect: "Check initial stack"
        stack.getStack().size() == 0
        and: "insert item to stack"
        stack.push(item)
        when: "poping from stack"
        String popedItem = stack.pop()
        then: "check for expected result"
        popedItem == expectedItem
        where:
        sno | item        | expectedItem    // Performing the push and pop function on to the stack.
        1   | "testItem1" | "testItem1"     // Pushing the same item and poping the same item
        2   | "testItem2" | "testItem2"
    }
    @Unroll("Testing pop() for multiple item: #sno")
    def "Testing pop() for multiple item"() {
        given: "initializing initial stack"
        StackImpl stack = new StackImpl();
        expect: "Check initial stack"
        stack.getStack().size() == 0
        and: "insert items to stack"
        stack.push(item1)
        stack.push(item2)
        stack.push(item3)
        and: "create popedItem reference"
        String popedItem
        when: "poping from stack"
        popedItem = stack.pop()
        then: "check for expected result"
        popedItem == item3
        when: "poping from stack"
        popedItem = stack.pop()
        then: "check for expected result"
        popedItem == item2
        when: "poping from stack"
        popedItem = stack.pop()
        then: "check for expected result"
        popedItem == item1
        where:
        sno | item1       | item2       | item3
        1   | "testItem1" | "testItem2" | "testItem3"
        2   | "test1"     | "test2"     | "test3"
    }
    def "Testing push for exception"() {
        given:
        StackImpl stack = new StackImpl()
        when:
        stack.push(new Integer(12))   // Here we are inserting Integer instead of String so it will throw
        // MissingMethodException
        then:
        // thrown(Exception)
        thrown(MissingMethodException)
        println("Trying to push integer instead of string")
    }
    def "Testing pop for empty stack"() {
        setup:
        StackImpl stack = new StackImpl()
        expect:"Expect size of stack as empty"
        stack.getStack().size() == 0
        when:"popping from stack"        // poping item from the stack it will throw an exception if stack is empty
        stack.pop()
        then:
        thrown(ArrayIndexOutOfBoundsException)
        println("Error due to stack is empty")
    }
}