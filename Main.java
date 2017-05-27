import ....;
public class Main {

  public static void main(String[] args) throws Exception {
  HashMap<String,Object> map=RSAUtils.getKeys();
    RSAPublicKey publicKey=(RSAPublicKey)map.get("public");
    RSAPrivateKey privateKey=(RSAPrivateKey)map.get("private");
    
    String publickeystr=writeToStr(publicKey);
    String privatekeystr=writeToStr(privateKey);
    
    String aespublic=AESUtils.AESEncode("",publickeystr);
    String aesprivate=AESUtils.AESEncode("",privatekeystr);
    
    //duqu jiami wenjian
    String aespublic=txt2String(new File("e:/public.key"));
    String aesprivate=txt2String(new File("e:/private.key"));
    
    String deaespublic=AESUtils.AESDncode("",aespublic);
    String deaesprivate=AESUtils.AESDncode("",aesprivate);
    
    RSAPublicKey publicKeyNow=(RSAPublicKey)deserializeFromStr(deaespublic);
    RSAPrivateKey privateKeyNow=(RSAPrivateKey)deserializeFromStr(deaesprivate);
    
    String ming="1111";
    String mi=RSAUtils.encryptByPublicKey(ming,publicKeyNow);
    
    String ming=RSAUtils.decryptByPrivateKey(mi,privateKeyNow);
  
  }
  
  public static String writeToStr(Object obj)throws IOException{
    ByteArrayOutputStream byteArrayOutputStream=new ByteArrayOutputStream();
    ObjectOutputStream objectOutputStream=new ObjectOutputStream(byteArrayOutputStream);
    objectOutputStream.writeObject(obj);
    String serStr=byteArrayOutputStream.toString("ISO-8859-1");
    serStr=java.net.URLEncoder.encoder(serStr,"UTF-8");
    objectOutputStream.close();
    byteArrayOutputStream.close();
    return serStr;
  }
  
  public static Object deserializeFromStr(String serStr)throws IOException,Exception{
  String redStr=java.net.URLDecoder.decoder(serStr,"UTF-8");
    ByteArrayInputStream byteArrayInputStream=new ByteArrayInputStream(redStr.getBytes("ISO-8859-1"));
    ObjectInputStream objectInputStream =new ObjectInputStream(byteArrayInputStream);
    Object obj=(Object)objectInputStream.readObject();
    objectInputStream.close();
    byteArrayInputStream.clouse();
    return obj;
  }
  
  public static String txt2String(File file){
  StringBuilder result=new StringBuilder();
    try{
    BufferedReader br=new BufferedReader(new FileReader(file));
      String s=null;
      while((s==br.readLine())!=null){
      result.append(System.lineSeparator()+s);
      }
      br.close();
      
    }catch(Exception e){
    e.printStackTrace()
    }
    return result.toString();
    
  }
  
  
  
  

//AESUtils http://www.cnblogs.com/liunanjava/p/4297854.html
  
  //RSAUtils  http://blog.csdn.net/centralperk/article/details/8558678
}
