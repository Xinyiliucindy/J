package jminusminus;

import java.util.ArrayList;
import java.util.concurrent.PriorityBlockingQueue;

import static jminusminus.CLConstants.*;


public class JInterfaceDeclaration extends JAST implements JTypeDecl{

    /** Interface modifiers. */
    private ArrayList<String> mods;

    /** Interface name. */
    private String name;

    /** Interface block. */
    private ArrayList<JMember> interfaceBlock;

    /** Interface extends */
    private ArrayList<Type> superInterfaces;

    /** Super interface type. */
    private Type superType;

    /** This interface type. */
    private Type thisType;

    /** Context for this interface. */
    private ClassContext context;


    /**
     * Constructs an AST node for a interface declaration given the line number, list
     * of interface modifiers, name of the interface, its super interface type, and the
     * interface block.
     * 
     * @param line
     *            line in which the interface declaration occurs in the source file.
     * @param mods
     *            interface modifiers.
     * @param name
     *            interface name.
     * @param superType
     *            super interface type.
     * @param interfaceBlock
     *            interface block.
     * @param superInterfaces
     *            super interfaces.
     */

    public JInterfaceDeclaration(int line, ArrayList<String> mods, String name, 
                            ArrayList<Type> superInterfaces, ArrayList<JMember> interfaceBlock) {
        super(line);
        this.mods = mods;
        this.name = name;
        this.superType = Type.OBJECT;
        this.interfaceBlock = interfaceBlock;
        this.superInterfaces = superInterfaces;
    }


    /**
     * Returns the interface name.
     * 
     * @return the interface name.
     */

    public String name() {
        return name;
    }

    /**
     * Returns the interface's super interface type.
     * 
     * @return the super interface type.
     */
    public Type superType() {
        return superType;
    }

    /**
     * Returns the type that this interface declaration defines.
     * 
     * @return the defined type.
     */

    public Type thisType() {
        return thisType;
    }

    /**
     * Returns the interface's super interface list.
     * 
     * @return the super interface type list.
     */
    public ArrayList<Type> superInterfaces() {
        return superInterfaces;
    }

    /**
     * Declares this interface in the parent (compilation unit) context.
     * 
     * @param context
     *            the parent (compilation unit) context.
     */
    public void declareThisType(Context context) {
        String qualifiedName = JAST.compilationUnit.packageName() == "" ? name : JAST.compilationUnit.packageName() + "/" + name;
        CLEmitter partial = new CLEmitter(false);
        partial.addClass(mods, qualifiedName, Type.OBJECT.jvmName(), null, false);
        thisType  = Type.typeFor(partial.toClass());
        context.addType(line, thisType);
    }



    /**
     * Pre-analyzes the members of this declaration in the parent context.
     * Pre-analysis extends to the member headers (including method headers) but
     * not into the bodies.
     * 
     * @param context
     *            the parent (compilation unit) context.
     */
    public void preAnalyze(Context context) {
        // Construct a interface context
        this.context = new ClassContext(this, context);

        // resolve superinterfaces
        for(int i = 0; i < superInterfaces.size(); i++){
            superInterfaces.set(i, superInterfaces.get(i).resolve(this.context));
        }

        // Creating a partial interface in memory can result in a
        // java.lang.VerifyError if the semantics below are
        // violated, so we can't defer these checks to analyze()
        while (superType != null) {
            thisType.checkAccess(line, superType);
        }
        if (superType.isFinal()) {
            JAST.compilationUnit.reportSemanticError(line,
            "Cannot extend a final type: %s", superType.toString());
        }

        // Create the (partial) interface
        CLEmitter partial = new CLEmitter(false);
        
    }


    @Override
    public JAST analyze(Context context) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void codegen(CLEmitter output) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void writeToStdOut(PrettyPrinter p) {
        p.printf("<JInterfaceDeclaration line=\"%d\" name=\"%s\">\n", line(), name);
        p.indentRight();

        if (context != null) {
            context.writeToStdOut(p);
        }

        if (mods != null) {
            p.println("<Modifiers>");
            p.indentRight();
            for (String mod : mods) {
                p.printf("<Modifier name=\"%s\"/>\n", mod);
            }
            p.indentLeft();
            p.println("</Modifiers>");
        }

        if(superInterfaces != null) {
            p.println("<Extends>");
            p.indentRight();
            for (Type superType : superInterfaces) {
                p.printf("<Extends name=\"%s\"/>\n", superType.toString());
            }
            p.indentLeft();
            p.println("</Extends>");
        }

        if (interfaceBlock != null) {
            p.println("<InterfaceBlock>");
            p.indentRight();
            for (JMember member : interfaceBlock) {
                ((JAST) member).writeToStdOut(p);
            }
            p.indentLeft();
            p.println("</InterfaceBlock>");
        }
        
        p.indentLeft();
        p.println("</JInterfaceDeclaration>");
    }
}
