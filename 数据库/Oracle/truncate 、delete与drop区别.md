��ͬ�㣺
1.truncate�Ͳ���where�Ӿ��delete���Լ�drop����ɾ�����ڵ����ݡ�
2.drop��truncate����DDL���(���ݶ�������),ִ�к���Զ��ύ��
��ͬ�㣺
1. truncate �� delete ֻɾ�����ݲ�ɾ����Ľṹ(����)
drop ��佫ɾ����Ľṹ��������Լ��(constrain)��������(trigger)������(index)�������ڸñ�Ĵ洢����/����������,���Ǳ�Ϊ invalid ״̬��
2. delete ��������ݿ��������(dml)�����������ŵ� rollback segement �У������ύ֮�����Ч���������Ӧ�� trigger��ִ�е�ʱ�򽫱�������
truncate��drop �����ݿⶨ������(ddl)������������Ч��ԭ���ݲ��ŵ� rollback segment �У����ܻع������������� trigger��
3.delete ��䲻Ӱ�����ռ�õ� extent����ˮ��(high watermark)����ԭλ�ò���
drop ��佫����ռ�õĿռ�ȫ���ͷš�
truncate ���ȱʡ����¼��ռ��ͷŵ� minextents�� extent������ʹ��reuse storage��truncate �Ὣ��ˮ�߸�λ(�ص��ʼ)��
4.�ٶȣ�һ����˵: drop> truncate > delete
5.��ȫ�ԣ�С��ʹ�� drop �� truncate������û�б��ݵ�ʱ��.����޶�������
ʹ����,��ɾ�������������� delete��ע�����where�Ӿ�. �ع���Ҫ�㹻��.
��ɾ����,��Ȼ�� drop
�뱣���������������ɾ��������������޹أ���truncate���ɡ�����������й�,�����봥��trigger,������delete��
�����������ڲ�����Ƭ��������truncate����reuse stroage�������µ���/�������ݡ�
6.delete��DML���,�����Զ��ύ��drop/truncate����DDL���,ִ�к���Զ��ύ��
7��TRUNCATE   TABLE   �ڹ������벻��   WHERE   �Ӿ��   DELETE   �����ͬ�����߾�ɾ�����е�ȫ���С���   TRUNCATE   TABLE   ��   DELETE   �ٶȿ죬��ʹ�õ�ϵͳ��������־��Դ�١�DELETE   ���ÿ��ɾ��һ�У�����������־��Ϊ��ɾ����ÿ�м�¼һ�TRUNCATE   TABLE   ͨ���ͷŴ洢���������õ�����ҳ��ɾ�����ݣ�����ֻ��������־�м�¼ҳ���ͷš�
8��TRUNCATE   TABLE   ɾ�����е������У�����ṹ�����С�Լ���������ȱ��ֲ��䡣���б�ʶ���õļ���ֵ����Ϊ���е����ӡ�����뱣����ʶ����ֵ�������   DELETE�����Ҫɾ�����弰�����ݣ���ʹ��   DROP   TABLE   ��䡣 
9��������   FOREIGN   KEY   Լ�����õı�����ʹ��   TRUNCATE   TABLE����Ӧʹ�ò���   WHERE   �Ӿ��   DELETE   ��䡣����   TRUNCATE   TABLE   ����¼����־�У����������ܼ��������   
10��TRUNCATE   TABLE   �������ڲ�����������ͼ�ı�
