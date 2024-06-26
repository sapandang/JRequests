package skd.requests;

import java.io.File;
import java.util.HashMap;

/**
 * @author sapan.dang
 */
public class MultiPartFormBody {


   private HashMap<String,String> textData = new HashMap<>();
   private HashMap<String,FileStruct> fileData = new HashMap<>();

    @Deprecated
    public MultiPartFormBody add(String key,String value)
    {
        FileStruct fileStruct = new FileStruct();
        fileStruct.key=key;
        fileStruct.value=value;
        fileData.put(key,fileStruct);
        return this;
    }

    public MultiPartFormBody add(String key,String contentType,String value)
    {
        FileStruct fileStruct = new FileStruct();
        fileStruct.key=key;
        fileStruct.value=value;
        fileStruct.contentType=contentType;
        fileData.put(key,fileStruct);
        return this;
    }


    public HashMap<String,String> getTextData()
    {
        return textData;
    }

    /**
     * Plan to remove this in the future
     * @param key
     * @param fileName
     * @param file
     * @return
     */
    @Deprecated
    public MultiPartFormBody add(String key,String fileName,File file)
    {
        FileStruct fileStruct = new FileStruct();
        fileStruct.key=key;
        fileStruct.fileName=fileName;
        fileStruct.file=file;
        fileStruct.type="file";
        fileStruct.contentType="text";
        fileData.put(key,fileStruct);
        return this;
    }


    public MultiPartFormBody add(String key,String fileName,String contentType,File file)
    {
        FileStruct fileStruct = new FileStruct();
        fileStruct.key=key;
        fileStruct.fileName=fileName;
        fileStruct.file=file;
        fileStruct.type="file";
        fileStruct.contentType=contentType;
        fileData.put(key,fileStruct);

        return this;
    }

    public HashMap<String,FileStruct> getFileData()
    {
        return fileData;
    }



    class FileStruct{

        public String key=null;
        public String fileName=null;
        public File file=null;
        public String contentType=null;
        public String value=null;
        public String type="text"; // text or file

    }


}
