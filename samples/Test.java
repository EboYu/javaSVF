import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;

import org.bytedeco.javacpp.*;
import org.cellscope.svf.*;
import static org.cellscope.svf.global.svf.*;

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
        BytePointer bp1 = new BytePointer("Demand-Driven Points-to Analysis\n");
        CLParseCommandLineOptions(arg_num,arg_value,bp1);

        SVFSVFModule module = SVFModuleCreate(moduleNameVec);
        SVFDDAPass dda = DDAPassCreate();
        DDAPassInitilize(dda,module);
        int i=0;

        CPAGNodeSetPtr nodeSet = DDAPassExtractAllValidPtrs(dda);

        i=0;
        for (CPAGNodeSetPtr.Iterator it = nodeSet.begin();
             it != nodeSet.end(); it.increment()) {
            if(i==nodeSet.size())
                break;
            i++;
            StringBuilder builder = new StringBuilder();

            CPAGNode_t node = it.second();
            builder.append(it.first()).append(":").append(node.variableType()).append(":").append(node.isTLPointer()).append(":");
            BytePointer functionName = node.functionName();
            builder.append(functionName.getString()).append(":");
            if(functionName.getString().equals("Glob"))
                builder.append(node.pointerName().getString());
            else
                builder.append(node.location().getString());
            if(node.variableType()==1)
                builder.append(":").append(node.irID());
            System.out.println(builder.toString());
        }


        /*BytePointer query = new BytePointer("169");
        DDAPassAnswerQuery(dda,query);
        System.out.println("Points-to Set for answering 169");
        PointsToMap nodePTSSet = new PointsToMap();
        DDAPassPointsToSet(dda, nodePTSSet);
        i=0;
        for (PointsToMap.Iterator it = nodePTSSet.begin();
             it != nodePTSSet.end(); it.increment()) {
            if(i==nodePTSSet.size())
                break;
            long sourceId = it.first();
            i++;

            StringBuilder builder = new StringBuilder();
            builder.append("S:").append(sourceId).append("==>");
            LongSet targets = it.second();
            int j =0;

            for(LongSet.Iterator iit=targets.begin();iit!=targets.end();iit.increment()){
                if(j==targets.size())
                    break;
                j++;
                builder.append(iit.get()).append(",");
            }
            System.out.println(builder.toString());
        }


        query = new BytePointer("175");

        DDAPassAnswerQuery(dda,query);
        System.out.println("Points-to Set for answering 175");
        nodePTSSet = new PointsToMap();
        DDAPassPointsToSet(dda, nodePTSSet);
        i=0;
        for (PointsToMap.Iterator it = nodePTSSet.begin();
             it != nodePTSSet.end(); it.increment()) {
            if(i==nodePTSSet.size())
                break;
            long sourceId = it.first();
            i++;

            StringBuilder builder = new StringBuilder();
            builder.append("S:").append(sourceId).append("==>");
            LongSet targets = it.second();
            int j =0;

            for(LongSet.Iterator iit=targets.begin();iit!=targets.end();iit.increment()){
                if(j==targets.size())
                    break;
                j++;
                builder.append(iit.get()).append(",");
            }
            System.out.println(builder.toString());
        }

        long parentID = DDAPassGetParentNode(dda,660003);
        System.out.println("Get the parent node of the node 660003 is:"+parentID);*/
        //DDAPassRunOnModule(dda,module);
        BytePointer bp2 = new BytePointer(".dvf");
        SVFDumpModulesToFile(module,bp2);

//        Iterator it = nodeSet.Iterator();

        // DDAPassDispose(dda);
        // SVFSVFModuleDispose(module);

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
