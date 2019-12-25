import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import org.bytedeco.javacpp.*;
import org.bytedeco.svf.*;
import static org.bytedeco.svf.global.svf.*;

public class Test{

    public static void main (String[] args) throws UnsupportedEncodingException {
        //Loader.load();
        String[] argv = new String[6];
        argv[0]="-cxt";
        argv[1]="-query=all";
        argv[2]="-print-query-pts";
        argv[3]="--dump-top-pts";
        argv[4]="--dump-txt-pag";
        argv[5]="/home/yinbo/disk/workspace/SUPA_RESULT/example-mem2reg.ll";

        boolean b = starting();
        PointerPointer params = new PointerPointer(args);
        performSVF(args.length,params);
    }
}
