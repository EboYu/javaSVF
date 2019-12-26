import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import org.bytedeco.javacpp.*;
import org.bytedeco.svf.*;
import static org.bytedeco.svf.global.svf.*;

public class Test{

    public static void main (String[] args) throws UnsupportedEncodingException {
        //Loader.load();
        // String[] argv = new String[6];
        // argv[0]="-cxt";
        // argv[1]="-query=all";
        // argv[2]="-print-query-pts";
        // argv[3]="--dump-top-pts";
        // argv[4]="--dump-txt-pag";
        // argv[5]="/home/yinbo/disk/workspace/SUPA_RESULT/example-mem2reg.ll";

        boolean b = starting();

        StringVector moduleNameVec = new StringVector();
        PointerPointer arg_value = new PointerPointer((long)args.length);
        PointerPointer argv = new PointerPointer(args);
        int arg_num = SVFProcessArguments(args.length,argv,arg_value,moduleNameVec);
        SVFSVFModule module = SVFModuleCreate(moduleNameVec);
        SVFDDAPass dda = DDAPassCreate();
        DDAPassRunOnModule(dda,module);
        BytePointer bp2 = new BytePointer(".dvf", "UTF-8");
        SVFDumpModulesToFile(module,bp2);
        DDAPassPrintQueryPTS(dda, "result.txt");
        DDAPassDispose(dda);
        SVFSVFModuleDispose(module);

        // System.out.printf("The arg_num is:%s\n",arg_num.getString());
        // for(long i=0;;i++){
        //     String r = arg_value.getString(i);
        //     if(r!=null)
        //         System.out.printf("The %d th value in arg_value is:%s\n",i,r);
        //     else 
        //         break;
        // }
        // if(moduleNameVec!=null)
        // if(moduleNameVec.empty()){
        //     System.out.printf("Nothing in moduleNameVec\n");
        // }else{
        //     System.out.printf("The moduleNameVec is:%s\n",moduleNameVec.toString());
        // }
        // performSVF(args.length,argv);
    }
}
