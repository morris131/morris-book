new������
Boolean boolean = new Boolean(false);

��̬����valueOf
Boolean boolean = Boolean.valueOf(false);

�Զ�װ��
Boolean boolean = false;

����
�ȿ�һ�δ���
Boolean[] boolean1 = new Boolean[100];
Boolean[] boolean2 = new Boolean[100];
Boolean[] boolean3 = new Boolean[100];
for (int i = 0; i < 100;i++){
    boolean1[i] = Boolean.valueOf(1==1);
}
for (int i = 0;i < 100;i++){
    boolean2[i] = new Boolean(1==1);
}
for (int i = 0; i < 100;i++){
    boolean3[i] = 1==1;
}
System.out.println("valueOf: " + String.valueOf(boolean1[1] == boolean1[2]));
System.out.println("new Boolean: " + String.valueOf(boolean2[1] == boolean2[2]));
System.out.println("auto wrap: " + String.valueOf(boolean3[1] == boolean3[2]));
�������ǣ�
valueOf: true
new Boolean: false
auto wrap: true
Ϊʲô�������أ�
ԭ���������new������Boolean�����ǲ��ϵ��´���һ��ʵ�����󣬶�valueOf���Ƿ���Boolean����ľ�̬��Ա�������������������ͬ��ʵ��������
�鿴�Զ�װ��classԴ�룬���ֵײ㻹�ǵ��õ�Boolean.valueOf������
    0  iconst_0
     1  invokestatic java.lang.Boolean.valueOf(boolean) : java.lang.Boolean [16]
     4  astore_1 [i1]
     5  iconst_0
     6  invokestatic java.lang.Boolean.valueOf(boolean) : java.lang.Boolean [16]
     9  astore_2 [i2]
ʵ����jdk�ĵ�Ҳ�ǽ�����valueOf����new��ʽ������Boolean�����