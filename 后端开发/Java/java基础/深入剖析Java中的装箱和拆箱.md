�Զ�װ��Ͳ���������Java��һ��������̸�������ˣ��������Ǿ���һЩ��һ��װ��Ͳ����е��������⡣�����Ƚ���װ��Ͳ���������Ķ�����������һ�����Ա����о�����������װ�䡢������ص����⡣

���������Ǳ��ĵ�Ŀ¼��٣�

����һ.ʲô��װ�䣿ʲô�ǲ��䣿

������.װ��Ͳ��������ʵ�ֵ�

������.��������ص�����

�������в���֮�������½������ָ������ʤ�м���

���������������Ͷ��ɹ���ת�������ԭ�����ӣ�

 ����http://www.cnblogs.com/dolphin0520/p/3780005.html

һ.ʲô��װ�䣿ʲô�ǲ��䣿

������ǰ����������ᵽ��JavaΪÿ�ֻ����������Ͷ��ṩ�˶�Ӧ�İ�װ�����ͣ�����Ϊʲô��Ϊÿ�ֻ������������ṩ��װ�������ڴ˲����в���������Ȥ�����ѿ��Բ���������ϡ���Java SE5֮ǰ�����Ҫ����һ����ֵΪ10��Integer���󣬱����������У�

1
Integer i = new Integer(10);
�������ڴ�Java SE5��ʼ���ṩ���Զ�װ������ԣ����Ҫ����һ����ֵΪ10��Integer����ֻ��Ҫ�����Ϳ����ˣ�

1
Integer i = 10;
������������л��Զ�������ֵ������Ӧ�� Integer���������װ�䡣

������ʲô�ǲ����أ�����˼�壬��װ���Ӧ�������Զ�����װ������ת��Ϊ�����������ͣ�

1
2
Integer i = 10;  //װ��
int n = i;   //����
������һ��˵��װ�����  �Զ���������������ת��Ϊ��װ�����ͣ��������  �Զ�����װ������ת��Ϊ�����������͡�

�����±��ǻ����������Ͷ�Ӧ�İ�װ�����ͣ�

int��4�ֽڣ�	Integer
byte��1�ֽڣ�	Byte
short��2�ֽڣ�	Short
long��8�ֽڣ�	Long
float��4�ֽڣ�	Float
double��8�ֽڣ�	Double
char��2�ֽڣ�	Character
boolean��δ����	Boolean
��.װ��Ͳ��������ʵ�ֵ�

������һС���˽�װ��Ļ�������֮����һС�����˽�һ��װ��Ͳ��������ʵ�ֵġ�

�������Ǿ���Interger��Ϊ�������濴һ�δ��룺

1
2
3
4
5
6
7
public class Main {
    public static void main(String[] args) {
          
        Integer i = 10;
        int n = i;
    }
}
����������class�ļ�֮��õ��������ݣ�

����

�����ӷ�����õ����ֽ������ݿ��Կ�������װ���ʱ���Զ����õ���Integer��valueOf(int)���������ڲ����ʱ���Զ����õ���Integer��intValue������

����������Ҳ���ƣ�����Double��Character�������ŵ����ѿ����Լ��ֶ�����һ�¡�

������˿�����һ�仰�ܽ�װ��Ͳ����ʵ�ֹ��̣�

����װ�������ͨ�����ð�װ����valueOf����ʵ�ֵģ������������ͨ�����ð�װ���� xxxValue����ʵ�ֵġ���xxx�����Ӧ�Ļ����������ͣ���

��.��������ص�����

������Ȼ������˶�װ��Ͳ���ĸ����������������Ժͱ�������������װ��Ͳ��������ȴ��һ�����������������о�һЩ��������װ��/�����йص������⡣

1.������δ������������ʲô��

1
2
3
4
5
6
7
8
9
10
11
12
public class Main {
    public static void main(String[] args) {
          
        Integer i1 = 100;
        Integer i2 = 100;
        Integer i3 = 200;
        Integer i4 = 200;
          
        System.out.println(i1==i2);
        System.out.println(i3==i4);
    }
}
����Ҳ����Щ���ѻ�˵�������false������Ҳ�����ѻ�˵�������true��������ʵ���������ǣ�


truefalse
 ����Ϊʲô����������Ľ��������������i1��i2ָ�����ͬһ�����󣬶�i3��i4ָ����ǲ�ͬ�Ķ��󡣴�ʱֻ��һ��Դ���֪������������δ�����Integer��valueOf�����ľ���ʵ�֣�

���ƴ���

public static Integer valueOf(int i) {

        if(i >= -128 && i <= IntegerCache.high)

            return IntegerCache.cache[i + 128];

        elsereturn new Integer(i);

    }
���ƴ���
����������IntegerCache���ʵ��Ϊ��

���ƴ���

���ƴ���
 private static class IntegerCache {

        static final int high;

        static final Integer cache[];



        static {

            final int low = -128;



            // high value may be configured by propertyint h = 127;

            if (integerCacheHighPropValue != null) {

                // Use Long.decode here to avoid invoking methods that

                // require Integer's autoboxing cache to be initializedint i = Long.decode(integerCacheHighPropValue).intValue();

                i = Math.max(i, 127);

                // Maximum array size is Integer.MAX_VALUE

                h = Math.min(i, Integer.MAX_VALUE - -low);

            }

            high = h;



            cache = new Integer[(high - low) + 1];

            int j = low;

            for(int k = 0; k < cache.length; k++)

                cache[k] = new Integer(j++);

        }



        private IntegerCache() {}

    }
���ƴ���
���ƴ���
��������2�δ�����Կ�������ͨ��valueOf��������Integer�����ʱ�������ֵ��[-128,127]֮�䣬�㷵��ָ��IntegerCache.cache���Ѿ����ڵĶ�������ã����򴴽�һ���µ�Integer����

��������Ĵ�����i1��i2����ֵΪ100����˻�ֱ�Ӵ�cache��ȡ�Ѿ����ڵĶ�������i1��i2ָ�����ͬһ�����󣬶�i3��i4���Ƿֱ�ָ��ͬ�Ķ���

2.������δ������������ʲô��

1
2
3
4
5
6
7
8
9
10
11
12
public class Main {
    public static void main(String[] args) {
          
        Double i1 = 100.0;
        Double i2 = 100.0;
        Double i3 = 200.0;
        Double i4 = 200.0;
          
        System.out.println(i1==i2);
        System.out.println(i3==i4);
    }
}
����Ҳ���е����ѻ���Ϊ������һ����Ŀ����������ͬ��������ʵ��ȴ���ǡ�ʵ��������Ϊ��


falsefalse
�������ھ���Ϊʲô�����߿���ȥ�鿴Double���valueOf��ʵ�֡�

����������ֻ����һ��ΪʲôDouble���valueOf�����������Integer���valueOf������ͬ��ʵ�֡��ܼ򵥣���ĳ����Χ�ڵ�������ֵ�ĸ��������޵ģ���������ȴ���ǡ�

����ע�⣬Integer��Short��Byte��Character��Long�⼸�����valueOf������ʵ�������Ƶġ�

����������Double��Float��valueOf������ʵ�������Ƶġ�

3.������δ�����������ʲô��

1
2
3
4
5
6
7
8
9
10
11
12
public class Main {
    public static void main(String[] args) {
          
        Boolean i1 = false;
        Boolean i2 = false;
        Boolean i3 = true;
        Boolean i4 = true;
          
        System.out.println(i1==i2);
        System.out.println(i3==i4);
    }
}
�����������ǣ�


truetrue
��������Ϊʲô����������ͬ���أ�����Boolean���Դ��Ҳ��һĿ��Ȼ��������Boolean��valueOf�����ľ���ʵ�֣�


public static Boolean valueOf(boolean b) {

        return (b ? TRUE : FALSE);

    }
���������е� TRUE ��FALSE����ʲô�أ���Boolean�ж�����2����̬��Ա���ԣ�

���ƴ���

���ƴ���
 public static final Boolean TRUE = new Boolean(true);



    /** 

     * The <code>Boolean</code> object corresponding to the primitive 

     * value <code>false</code>. 

     */public static final Boolean FALSE = new Boolean(false);
���ƴ���
���ƴ���
�������ˣ����Ӧ��������Ϊ����������Ľ������true�ˡ�

4.̸̸Integer i = new Integer(xxx)��Integer i =xxx;�����ַ�ʽ������

������Ȼ�������Ŀ���ڱȽϿ����͵ġ�����Ҫ��һ��Ҫ���ϣ����ܽ�һ����Ҫ����������������

����1����һ�ַ�ʽ���ᴥ���Զ�װ��Ĺ��̣����ڶ��ַ�ʽ�ᴥ����

����2����ִ��Ч�ʺ���Դռ���ϵ����𡣵ڶ��ַ�ʽ��ִ��Ч�ʺ���Դռ����һ���������Ҫ���ڵ�һ�������ע���Ⲣ���Ǿ��Եģ���

5.����������������ʲô��

1
2
3
4
5
6
7
8
9
10
11
12
13
14
15
16
17
18
19
20
21
public class Main {
    public static void main(String[] args) {
          
        Integer a = 1;
        Integer b = 2;
        Integer c = 3;
        Integer d = 3;
        Integer e = 321;
        Integer f = 321;
        Long g = 3L;
        Long h = 2L;
          
        System.out.println(c==d);
        System.out.println(e==f);
        System.out.println(c==(a+b));
        System.out.println(c.equals(a+b));
        System.out.println(g==(a+b));
        System.out.println(g.equals(a+b));
        System.out.println(g.equals(a+h));
    }
}
�����ȱ��������������Լ���һ����δ������������ʲô����������Ҫע����ǣ��� "=="��������������������� ��װ�����͵����ã����ǱȽ�ָ����Ƿ���ͬһ�����󣬶����������һ���������Ǳ��ʽ���������������㣩��Ƚϵ�����ֵ�����ᴥ���Զ�����Ĺ��̣������⣬���ڰ�װ�����ͣ�equals�����������������ת������������2��֮���������������һĿ��Ȼ��

���ƴ���

���ƴ���
truefalsetruetruetruefalsetrue
���ƴ���
���ƴ���
������һ���͵ڶ���������û��ʲô���ʡ�����������  a+b�������������㣬��˻ᴥ���Զ�������̣������intValue��������������ǱȽϵ�����ֵ�Ƿ���ȡ�������c.equals(a+b)���ȴ����Զ�������̣��ٴ����Զ�װ����̣�Ҳ����˵a+b�����ȸ��Ե���intValue�������õ��˼ӷ���������ֵ֮�󣬱����Integer.valueOf�������ٽ���equals�Ƚϡ�ͬ����ں����Ҳ������������Ҫע�⵹���ڶ��������һ������Ľ���������ֵ��int���͵ģ�װ����̵��õ���Integer.valueOf�������long���͵ģ�װ����õ�Long.valueOf��������

���������ľ���ִ�й��������ʣ����Գ��Ի�ȡ��������ֽ������ݽ��в鿴��

