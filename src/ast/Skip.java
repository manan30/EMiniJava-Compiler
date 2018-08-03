package ast;

/* Skip represents empty statement.
 * It is useful e.g. as "else" block of and if-thenStatement-else statement
 */
public class Skip extends Statement {
    public Skip() {
    }

    public <R> R accept(Visitor<R> v) {
        return v.visit(this);
    }
}