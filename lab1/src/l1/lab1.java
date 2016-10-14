package l1;
import java.util.*;
public class lab1 {
	public static int N=200;
	public static String str,strin;										//����
	public static Vector<Vector<STR>> S=new Vector<Vector<STR>>();		//�������
	public static String strtemp;										//��ɸ�ֵ֮�������
	public static void main(String args[])
    { 
		expression();	//����
    }
	public static void expression()
	{
		java.util.Scanner sc = new Scanner(System.in);
      	str=strin=sc.nextLine();		//����
      	while (!"!!".equals(strin))
      	{
      		if (strin.charAt(0)!='!')
      		{
      			str=strin;
      			str=str.replace(" ","");		//ȥ���ո�
      			str=str.replace("	","");		//ȥ���Ʊ��
      			System.out.println(str);		//���ʽ
      		}
      		else 
      		{
      			if (strin.contains("d/d"))
      			{
      				derivative();				//��
      			}
      			else if (strin.contains("simplify")) 
      			{
  				 	simplify();					//��ֵ
      			}
      			else
      				System.out.println("ERROR!");
      		}
      		strin=sc.nextLine();
      	}
      	sc.close();
	}//endof expression()
	public static void simplify()
	{
			int lengthofstrin=strin.length();	//ԭ���г���
			int count=9;
			strtemp=str;
			int nnn=0;	//����ֵ��y= ���ĸ���
			while (count<lengthofstrin)		//��ȡ����ֵ�ı�������
			{
				String val=""; 
				while (strin.charAt(count)==' ' || strin.charAt(count)==(char)9) count++;
				while (strin.charAt(count)!='=') 
				{
					val += strin.charAt(count);
					count ++;
					while (strin.charAt(count)==' ' || strin.charAt(count)==(char)9) count++;
				}
				if (str.indexOf(val)==-1) 
				{
					System.out.println("ERROR!");
					return;
				}
				String number="";
				count ++;
				if (count>=lengthofstrin) 
				{
					continue;
				}
				//while (strin.charAt(count)==' ' || strin.charAt(count)==(char)9) count++;
				while ((strin.charAt(count)>='0' && strin.charAt(count)<='9')||(strin.charAt(count)>='a' && strin.charAt(count)<='z' ) ||strin.charAt(count)=='.') 
				{
					number += strin.charAt(count);
					if (++count>=lengthofstrin-1) break;
				}
				if (number=="") 
				{
					continue;
				}
				strtemp=strtemp.replace(val, number);		//��ֵ�滻
				nnn++;
				count++;
				if (count>=lengthofstrin-1) break;  
			}//endof while()
			//System.out.println(strtemp);
			if(nnn==0) 			//������ֵ�����
			{
				System.out.println(str);
				return;
			}
			pretreatment();			//���滻��� �����н���ת��׺�����������
			int printorder=0;
			for (int i=0;i<S.elementAt(S.size()-1).size();i++)		//���֡��ַ��������ֿ����
			{
				STR NOW=S.elementAt(S.size()-1).elementAt(i);
				Vector<String> nowstr=NOW.getmString();
				float nownum=NOW.getmNumber();
				if (nownum!=0)
				{
					if (printorder!=0 && nownum>0) System.out.print('+'); 
					printorder++;
					System.out.print(nownum);
					if (nowstr==null) continue;
					for (int j=0;j<nowstr.size();j++)
						System.out.print("*"+nowstr.elementAt(j));
					
				}	
			}//endof for
			System.out.println();
	}//endof simplify()
	public static void derivative()
	{
		int lengthofstrin=strin.length();
		int count=4;
		String val="";	//���󵼵ı�������
		while (strin.charAt(count)==' ' || strin.charAt(count)==(char)9) count++;
		while (count<lengthofstrin) 		//��ȡ���󵼵ı�������
		{
			val += strin.charAt(count);
			count ++;
			if (count>=lengthofstrin)break;
			while (strin.charAt(count)==' ' || strin.charAt(count)==(char)9) count++;
		}
		if (str.indexOf(val)==-1) 
		{
			System.out.println("ERROR!");
			return;
		}
		strtemp=str;
		pretreatment();
		int[] tempn=new int[N];			//�洢�������λ��
		int temp=0;
 		for (int i=0;i<S.elementAt(S.size()-1).size();i++)	//�����󵼣�������������¼������
		{
			STR NOW=S.elementAt(S.size()-1).elementAt(i);
			Vector<String> nowstr=NOW.getmString();
			if (nowstr==null) {tempn[temp++]=i;continue;}
			 if (nowstr.contains(val))
			{
				 int tempm=1;
				nowstr.removeElement(val);
				Vector<String> nowstrr;
				nowstrr=(Vector<String>) nowstr.clone();
				float nownumm=S.elementAt(S.size()-1).elementAt(i).getmNumber();
				S.elementAt(S.size()-1).elementAt(i).setmString(nowstr);
				while (nowstrr.contains(val))
				{
					tempm++;
					nowstrr.removeElement(val);
				}
				S.elementAt(S.size()-1).elementAt(i).setmNumber(nownumm*tempm);
			}	
			else 
			{
				tempn[temp++]=i;
			}
		}
 		for (int i=0;i<temp;i++)S.elementAt(S.size()-1).removeElementAt(tempn[temp]);	//ɾ��������
		int printorder=0;			//�����Ĵ���
		for (int i=0;i<S.elementAt(S.size()-1).size();i++)		//	�ַ����ַֿ����
		{
			STR NOW=S.elementAt(S.size()-1).elementAt(i);
			Vector<String> nowstr=NOW.getmString();
			float nownum=NOW.getmNumber();
			{
				if (nownum!=0)
				{
					if (printorder!=0 && nownum>0) System.out.print('+'); 
					System.out.print(nownum);
					if (nowstr==null) continue;
					for (int j=0;j<nowstr.size();j++)
						System.out.print("*"+nowstr.elementAt(j));
					printorder++;
				}
			}			 
		}//endof for
		System.out.println();
	}//endof derivative()
	public static void pretreatment()
	{
		STRINGG[] STA = new STRINGG[N]; //�洢��׺���ʽ
		for (int i=0;i<N;i++)		//��ʼ��
		{
			STRINGG nSTA=new STRINGG(0, 0 , 0 ,(char)0, "");
			STA[i] = nSTA;
		}
		int[] cmpn=new int[N]; //����������ȼ���ȷ��
		cmpn[(int)'-']=1;
		cmpn[(int)'+']=1;
		cmpn[(int)'*']=2;
		cmpn[(int)'^']=3;
		cmpn[(int)'(']=0;
		int sum=0;
		String f=strtemp;		//����������
		String r1="",r2="";	
		int ii=0;
		int fla=1;
		int fl2=0;
		for (int i=0;i<f.length();i++)	//��׺���ʽ �Ĺ���
		{
			if ( (f.charAt(i)>='0' && f.charAt(i)<='9' ) || (f.charAt(i)>='a' && f.charAt(i)<='z' ))
			{
				r1+=f.charAt(i);
			}
			else if (f.charAt(i)=='.')
			{
				r1+=f.charAt(i);
				fl2=1;
			}
			else
			{
				if (    (f.charAt(i)=='-') && (   	(  ( ( f.charAt(i+1)>='0' && f.charAt(i+1)<='9') ||( f.charAt(i+1)>='a' && f.charAt(i+1)<='z') )
									&& !(f.charAt(i-1)>='0' && f.charAt(i-1)<='9') && !(f.charAt(i-1)>='a' && f.charAt(i-1)<='z'))||i==0      )   )
				{
							if ((f.charAt(i+1)>='0' && f.charAt(i+1)<='9')||f.charAt(i+1)>='a' && f.charAt(i+1)<='z')
							{
								fla*=-1;
								continue;
							}
							else if  (f.charAt(i+1)=='(')
							{
								r2+=f.charAt(i);
								ii++;
								continue;
							}
				}
				if (i!=0)
				if ((f.charAt(i-1)>='0' && f.charAt(i-1)<='9')||(f.charAt(i-1)>='a' && f.charAt(i-1)<='z'))
				{
							if (fl2==0)
							{
								if (r1.charAt(0)>='0'&&r1.charAt(0)<='9')
								{
						 		int n = Integer.parseInt(r1);
						 		n*=fla;
						 		fla=1;
						 		//////////System.out.print(n+" ");
								STA[sum].setIntn(n);
								STA[sum].setFlag(0);
								}
								else
								{
									/////////System.out.print(r1+" ");
									STA[sum].setVal(r1);;
									STA[sum].setFlag(3);
								}
								r1="";
								sum++;
							}
							else
							{
								float n=Float.parseFloat(r1);
								n*=(float)fla;
								fla=1;
								r1="";
								STA[sum].setFlo(n);
								STA[sum].setFlag(2);
								sum++;
								fl2=0;
							}
				}//endof if 
				char ff=f.charAt(i);
				if (ii==0) 
				{
							r2+=ff;
							ii++;
				}
				else if (ff=='(') 
				{
							r2+=ff;
							ii++;
				}
				else if (ff==')')
				{
							while (r2.charAt(ii-1)!='(')
							{
								//////System.out.print(r2.charAt(ii-1)+" ");
								STA[sum].setChr(r2.charAt(ii-1));
								STA[sum].setFlag(1);
								sum++;
								ii--;
								r2=r2.substring(0, r2.length()-1);
							}
							ii--;
							r2=r2.substring(0, r2.length()-1);
				}
				else
				{
							while (cmpn[(int)r2.charAt(ii-1)]>=cmpn[(int)ff])
							{
								if (ii==0)break;
								/////System.out.print(r2.charAt(ii-1)+" ");
								STA[sum].setChr(r2.charAt(ii-1));
								STA[sum].setFlag(1);
								sum++;
								ii--;
								r2=r2.substring(0, r2.length()-1);
								if (ii==0)break;
							}
							r2+=ff;
							ii++;
				}//endof if (ii==0)					
			}//endof if ( (f.charAt(i)>='0' && f.charAt(i)<='9' )
		}//endof for 
		if ((f.charAt(f.length()-1)>='0'&&f.charAt(f.length()-1)<='9')
				||(f.charAt(f.length()-1)>='a'&&f.charAt(f.length()-1)<='z')) //�������һλ
		{
				if (fl2==0)
				{
					if (r1.charAt(0)>='0'&&r1.charAt(0)<='9')
					{
			 		int n = Integer.parseInt(r1);
			 		n*=fla;
			 		fla=1;
					STA[sum].setIntn(n);
					STA[sum].setFlag(0);
					}
					else
					{
						STA[sum].setVal(r1);;
						STA[sum].setFlag(3);
					}
					r1="";
					sum++;
				}
				else
				{
						float n=Float.parseFloat(r1);
						n*=(float)fla;
						fla=1;
						r1="";
						STA[sum].setFlo(n);
						STA[sum].setFlag(2);
						sum++;
						fl2=0;
				}
		}//endof if ((f.charAt(f.length()-1)>='0 
		while(ii>0)
		{
			STA[sum].setChr(r2.charAt(ii-1));
			STA[sum].setFlag(1);
			sum++;
			ii--;
		}//endof while()
		STR temp;		
		Vector<STR> tempv; 			//��������Ҫ����ʱ�������Ϻ������������
		temp =new STR(null, 0, 1);
		tempv=new Vector<STR>();
		tempv.add(temp);
		S.add(tempv);				//��ʼ��
		for (int i=0;i<sum;i++)		//��׺���ʽ����ֵ
		{
				if (STA[i].getFlag()==0) 	//����
				{
					temp =new STR(null, STA[i].getIntn(),1);
					tempv=new Vector<STR>();
					tempv.add(temp);
					S.add(tempv);
				}
				else if (STA[i].getFlag()==2)		//ʵ��
				{
					temp =new STR(null, STA[i].getFlo(),1);
					tempv=new Vector<STR>();
					tempv.add(temp);
					S.add(tempv);
				}
				else if (STA[i].getFlag()==3)		//�ַ�������
				{
					temp =new STR(null,1,2);
					Vector<String> sss=new Vector<String>();
					sss.add(STA[i].getVal());
					temp.setmString(sss);
					tempv=new Vector<STR>();
					tempv.add(temp);
					S.add(tempv);
				}
				else if (STA[i].getFlag()==1)		//����������м��㣩
				{
					if (STA[i].getChr()=='+')
					{
						add();
					}
					else if (STA[i].getChr()=='-')
					{
						sub();
					}
					else if (STA[i].getChr()=='*')
					{
						mul();
					}
					else if (STA[i].getChr()=='^')
					{
						ele();
					}
				}
		}//endof for 
	}// endof  pretreatment()
	public static void add() //�ӷ�����
	{
		Vector<STR> s1=S.elementAt(S.size()-1);//ȡ��ͷ����
		S.setSize(S.size()-1);
		Vector<STR> s2=S.elementAt(S.size()-1);
		S.setSize(S.size()-1);
		Vector<STR> ss=new Vector<STR>(); //��ʱ�������Ϻ������������
		for (int i=0;i<s1.size();i++)	//�����ַ������������Ķ���ʽ�ӷ�
			for (int j=0;j<s2.size();j++)
			{
				int s1iflag=(int) s1.elementAt(i).getflag();
				float s1inum=(float) s1.elementAt(i).getmNumber();
				Vector<String> s1istr = (Vector<String>) s1.elementAt(i).getmString();
				int s2jflag=(int) s2.elementAt(j).getflag();
				float s2jnum=(float) s2.elementAt(j).getmNumber();
				Vector<String> s2jstr = (Vector<String>) s2.elementAt(j).getmString();
				if (s1iflag==s2jflag) //�Ƚ��Ƿ���ͬ����
				{
					if (s1iflag==2)		//�ַ�����ļӷ�����
					{
						String aa="",bb="";
						for (int l=0;l<s2jstr.size();l++){bb+=s2jstr.elementAt(l);}
						for (int l=0;l<s1istr.size();l++){aa+=s1istr.elementAt(l);}
						if (aa.equals(bb))
						{
							s1.elementAt(i).setmNumber(s1inum+s2jnum);
							ss.addElement(s1.elementAt(i));
						}
						else		//������ļӷ�����
						{
							ss.addElement(s1.elementAt(i));
							ss.addElement(s2.elementAt(i));
						}
					}
					else	
					{
						
						s1.elementAt(i).setmNumber(s1inum+s2jnum);
						ss.addElement(s1.elementAt(i));
					}
				}
				else	//��ͬ����ļӷ�����
				{
					int ff1=0;
					int ff2=0;
					for (int k=0;k<ss.size();k++)		//Ѱ����ʱ�����Ƿ��п��Ժϲ���ͬ����
					{
						int sskflag=(int) ss.elementAt(k).getflag();
						float ssknum=(float) ss.elementAt(k).getmNumber();
						Vector<String> sskstr = (Vector<String>) ss.elementAt(k).getmString();
						if (sskflag==s1iflag)
						{
							ff1=1;
							if (sskflag==2)//�ж����������ַ���������Ժϲ���ͬ����
							{
								String aa="",bb="";
								for (int l=0;l<sskstr.size();l++){aa+=sskstr.elementAt(l);}
								for (int l=0;l<s1istr.size();l++){bb+=s1istr.elementAt(l);}
								if (aa.equals(bb)) //���Ժϲ���ͬ����
								{
									ss.elementAt(k).setmNumber(ssknum+s1inum);
								}
								else
								{
									ss.add(s1.elementAt(i));
								}
							}
							else
							{
								ss.elementAt(k).setmNumber(ssknum+s1inum);
							}
						}
						else if (sskflag==s2jflag)
						{
							ff2=1;
							if (sskflag==2)//�ж����������ַ���������Ժϲ���ͬ����
							{
								String aa="",bb="";
								for (int l=0;l<sskstr.size();l++){aa+=sskstr.elementAt(l);}
								for (int l=0;l<s2jstr.size();l++){bb+=s2jstr.elementAt(l);}
								if (aa.equals(bb)) //���Ժϲ���ͬ����
								{
									ss.elementAt(k).setmNumber(ssknum+s2jnum);
								}
								else
								{
									ss.add(s2.elementAt(i));
								}
							}
							else
							{
								ss.elementAt(k).setmNumber(ssknum+s2jnum);
							}
						}
					}//endof for (int k=0;k<ss.size();k++)
					if (ff1==0) {ss.add(s1.elementAt(i));}	//��ͬ������ֱ�Ӽ�����ʱ��
					if (ff2==0) {ss.add(s2.elementAt(j));}	//��ͬ������ֱ�Ӽ�����ʱ��
				}//endof if (s1iflag==s2jflag)
			}//endof for (int i=0;i<s1.size();i++)
		S.add(ss);  //��������ϵ���ʱ�����������
	}//endof add()
	public static void sub()
	{
		Vector<STR> s1=S.elementAt(S.size()-1);
		for (int i=0;i<s1.size();i++)
		{
			float num=s1.elementAt(i).getmNumber();		
			S.elementAt(S.size()-1).elementAt(i).setmNumber(num*-1);		//�������������ȡ��
		}
		add();		//ִ�мӷ�
	}//endof sub()
	public static void mul()
	{
		Vector<STR> s1=S.elementAt(S.size()-1);
		S.setSize(S.size()-1);
		Vector<STR> s2=S.elementAt(S.size()-1);
		S.setSize(S.size()-1);
		Vector<STR> ss=new Vector<STR>();		//��ʱ�������Ϻ������������
		for (int i=0;i<s1.size();i++)
			for (int j=0;j<s2.size();j++)		//�����ַ������������Ķ���ʽ�˷�
			{
				int s1iflag=(int) s1.elementAt(i).getflag();
				float s1inum=(float) s1.elementAt(i).getmNumber();
				Vector<String> s1istr = (Vector<String>) s1.elementAt(i).getmString();
				int s2jflag=(int) s2.elementAt(j).getflag();
				float s2jnum=(float) s2.elementAt(j).getmNumber();
				Vector<String> s2jstr = (Vector<String>) s2.elementAt(j).getmString();
				float ss1inum ;
				ss1inum=s1inum*s2jnum;
				int ss1iflag = s1iflag;
				if (s1iflag!=s2jflag) ss1iflag=2;
				Vector<String> ss1istr=new Vector<String>();
				if (s1istr!=null)for (int k=0;k<s1istr.size();k++)	ss1istr.add(s1istr.elementAt(k));
				if (s2jstr!=null)for (int k=0;k<s2jstr.size();k++)	ss1istr.add(s2jstr.elementAt(k));
				Collections.sort(ss1istr);  
				STR ne=new STR(ss1istr,ss1inum,ss1iflag);
				int flag=0;
				for (int k=0;k<ss.size();k++)			//�ж��Ƿ����ͬ����
				{
					Vector<String> stemp=ss.elementAt(k).getmString();
					String aa="",bb="";
					for (int l=0;l<stemp.size();l++) aa+=stemp.elementAt(l);
					for (int l=0;l<ss1istr.size();l++) bb+=ss1istr.elementAt(l);
					if (aa.equals(bb))
					{
						flag=1;
						ss.elementAt(k).setmNumber(ss.elementAt(k).getmNumber()+ss1inum);
						break;
					}
				}
				if (flag==0) ss.add(ne);
			}
		S.add(ss);
	}//endof mul()
	public static void ele()		//������
	{
		Vector<STR> s1=S.elementAt(S.size()-1);
		S.setSize(S.size()-1);
		Vector<STR> s2=S.elementAt(S.size()-1);
		int s1inum=(int) s1.elementAt(0).getmNumber();
		for (int i=0;i<s1inum-1;i++)		//�ȳ˺��
		{
			S.add(s2);
			mul();
		}
	}
	
}
class STRINGG
{
	private int flag;			//��������ͣ��ַ������֣��������
	private float flo;			//�洢ʵ��
	private int intn;			//�洢����
	private char chr;			//�洢����
	private String val;			//�洢�ַ�������
	public STRINGG(int flag,float flo,int intn,char chr,String val)
	{
        this.setFlag(flag);
        this.setFlo(flo);
        this.setIntn(intn);
        this.setChr(chr);
        this.setVal(val);
    }
	public int getFlag() {
		return flag;
	}
	public void setFlag(int flag) {
		this.flag = flag;
	}
	public float getFlo() {
		return flo;
	}
	public void setFlo(float flo) {
		this.flo = flo;
	}
	public int getIntn() {
		return intn;
	}
	public void setIntn(int intn) {
		this.intn = intn;
	}
	public char getChr() {
		return chr;
	}
	public void setChr(char chr) {
		this.chr = chr;
	}
	public String getVal() {
		return val;
	}
	public void setVal(String val) {
		this.val = val;
	}
}
class STR
{
	private Vector<String> mString;		//����е��ַ���
	private float mNumber;				//����е�������
	private int flag;					//����������
	public STR(Vector<String> mString,float mNumber,int flag)
		{
	        this.setmString(mString);
	        this.setmNumber(mNumber);
	        this.setflag(flag);
	    }
	public Vector<String> getmString() {
		return mString;
	}
	public void setmString(Vector<String> mString) {
		this.mString = mString;
	}
	public float getmNumber() {
		return mNumber;
	}
	public void setmNumber(float mNumber) {
		this.mNumber = mNumber;
	}
	public float getflag() {
		return flag;
	}
	public void setflag(int flag) {
		this.flag = flag;
	}
}