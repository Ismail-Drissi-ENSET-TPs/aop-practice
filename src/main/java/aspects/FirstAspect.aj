package aspects;

public aspect FirstAspect {

    pointcut pc1() : execution(* com.ismaildrs.Main.main(..));

    before() : pc1() {
        System.out.println("=============");
        System.out.println("BEFORE");
        System.out.println("=============");
    }

    after() : pc1() {
        System.out.println("=============");
        System.out.println("AFTER");
        System.out.println("=============");
    }
}
