package com.morris.pattern.composite;

public class Client {
    public static void main(String[] args) {

        ConcreteCompany root = new ConcreteCompany("某软件公司");

        //部门经理，既是树节点，也是上级的子节点
        ConcreteCompany developDep = new ConcreteCompany("研发部");
        ConcreteCompany salesDep = new ConcreteCompany("销售部");
        ConcreteCompany finaceDep = new ConcreteCompany("财务部");

        //把三个经理添加到公司框架中
        root.add(developDep);
        root.add(salesDep);
        root.add(finaceDep);

        //部门员工
        Employee e1 = new Employee("A");
        Employee e2 = new Employee("B");
        Employee e3 = new Employee("C");
        Employee e4 = new Employee("D");
        Employee e5 = new Employee("E");
        Employee e6 = new Employee("F");
        Employee e7 = new Employee("G");
        Employee e8 = new Employee("H");

        //把底层员工添加到特定的区域
        developDep.add(e1);//研发部门
        developDep.add(e2);
        developDep.add(e3);
        salesDep.add(e4);//销售部门
        salesDep.add(e5);
        salesDep.add(e6);
        finaceDep.add(e7);//财务部门
        finaceDep.add(e8);

        // 查询公司员工和部门信息
        root.getInfo();
    }
}
