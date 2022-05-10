package jminusminus;

import java.util.ArrayList;

import static jminusminus.CLConstants.*;


public class JInterfaceDeclaration extends JAST implements JTypeDecl{

    /** Interface modifiers. */
    private ArrayList<String> mods;

    /** Interface name. */
    private String name;

    /** Interface block. */
    private ArrayList<JMember> interfaceBlock;

    /** super interface type */
    private ArrayList<Type> superInterfaces;

    /** This interface type. */
    private Type thisType;

    /** Context for this interface. */
    private ClassContext context;

    private Type superType;

    private ArrayList<JFieldDeclaration> staticFieldInitializations;


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
        this.interfaceBlock = interfaceBlock;
        this.superInterfaces = superInterfaces;
        this.staticFieldInitializations = new ArrayList<>();
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

    public Type superType() {
        return this.superType;
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

        // resolve each superinterface in the list of superinterfaces
        Type resolved = null;
        for(int i = 0; i < superInterfaces.size(); i++) {
            resolved = superInterfaces.get(i).resolve(this.context);
            superInterfaces.set(i, resolved);
        }

        // check accessibility of each superinterface
        for(Type superinterface: superInterfaces) {
            thisType.checkAccess(line, superinterface);
            // superinterface can't be final
            if (superinterface.isFinal()) {
                JAST.compilationUnit.reportSemanticError(line,
                "Cannot extend a final type: %s", superinterface.toString());
            }
        }

        // Create the (partial) interface
        CLEmitter partial = new CLEmitter(false);

        // Add the class header to the partial class
        String qualifiedName = JAST.compilationUnit.packageName() == "" ? name
                : JAST.compilationUnit.packageName() + "/" + name;

        ArrayList<String> superInterfacesNames = new ArrayList<>();
        for(Type superinterface: superInterfaces) {
            superInterfacesNames.add(superinterface.jvmName());
        }
        partial.addClass(mods, qualifiedName, Type.OBJECT.jvmName(), superInterfacesNames, false);
        
        // Pre-analyze the members and add them to the partial
        // interface: 
        // 1. method can't be private
        // 2. method should be abstract
        // 3. method must not be implemented
        for (JMember member : interfaceBlock) {         
            if (member instanceof JMethodDeclaration) {
                JMethodDeclaration aMethod = (JMethodDeclaration) member;
                aMethod.preAnalyze(this.context, partial);
                if (aMethod.isPrivate || aMethod.mods.contains(TokenKind.STATIC.image()) || aMethod.mods.contains(TokenKind.FINAL.image())) {
                    JAST.compilationUnit.reportSemanticError(line(), "Private/static/final method %s is not allowed in an interface", aMethod.toString());
                }
                if (!aMethod.mods.contains("abstract")) {
                    aMethod.mods.add("abstract");
                    aMethod.isAbstract = true;
                }
                if (!aMethod.mods.contains("public")) {
                    aMethod.mods.add("public");
                    aMethod.isPublic = true;
                }
                if (aMethod.body != null) {
                    JAST.compilationUnit.reportSemanticError(line(), "Method %s is not allowed to have body in an interface", aMethod.toString());
                }
            }
            if(member instanceof JFieldDeclaration) {
                // Field: an interface should be public static
                JFieldDeclaration aField = (JFieldDeclaration) member;
                if (!aField.mods().contains(TokenKind.PUBLIC.image())) {
                    mods.add(TokenKind.PUBLIC.image());
                }
                if (!aField.mods().contains(TokenKind.STATIC.image())) {
                    mods.add(TokenKind.STATIC.image());
                }
                // aField.preAnalyze(this.context, partial);
            }
            member.preAnalyze(this.context, partial);
        }

        // interface does not have constructor

        // Get the Class rep for the (partial) class and make it
        // the
        // representation for this type
        Type id = this.context.lookupType(name);
        if (id != null && !JAST.compilationUnit.errorHasOccurred()) {
            id.setClassRep(partial.toClass());
        }
    }


    @Override
    public JAST analyze(Context context) {
        for(JMember member : interfaceBlock) {
            ((JAST) member).analyze(this.context);
        }

        // Copy declared fields for purposes of initialization.
        for(JMember member : interfaceBlock) {
            if(member instanceof JFieldDeclaration) {
                JFieldDeclaration fieldDecl = (JFieldDeclaration) member;
                if (!fieldDecl.mods().contains("public")) {
                    fieldDecl.mods().contains("public");
                }
                if (fieldDecl.mods().contains("static")) {
                    staticFieldInitializations.add(fieldDecl);
                } else {
                    JAST.compilationUnit.reportSemanticError(line(), "Field declaration in interface should be static", member.toString());
                    fieldDecl.mods().contains("static");
                } 
                if (!fieldDecl.mods().contains("final")) {
                    fieldDecl.mods().contains("final");
                }
            }
        }
        return this;
    }

    @Override
    public void codegen(CLEmitter output) {
        // The interface header
        String qualifiedName = JAST.compilationUnit.packageName() == "" ? name
                : JAST.compilationUnit.packageName() + "/" + name;
        
        ArrayList<String> superInterfacesNames = new ArrayList<>();
        for(Type superinterface: superInterfaces) {
            superInterfacesNames.add(superinterface.jvmName());
        }
        output.addClass(mods, qualifiedName, Type.OBJECT.jvmName(), superInterfacesNames, false);   
        // System.out.println("Interface access flag: "+mods);

        // The members: recursively codegen members
        for (JMember member : interfaceBlock) {
            ((JAST) member).codegen(output);
        }

        // Generate a interface initialization method?
        if (staticFieldInitializations.size() > 0) {
            codegenInterfaceInit(output);
        }
    }

    /**
     * Generates code for interface initialization, in j-- this means static field
     * initializations.
     * 
     * @param output
     *            the code emitter (basically an abstraction for producing the
     *            .class file).
     */

    private void codegenInterfaceInit(CLEmitter output) {
        ArrayList<String> mods = new ArrayList<String>();
        mods.add("public");
        mods.add("static");
        mods.add("final");
        output.addMethod(mods, "<clinit>", "()V", null, false);

        // If there are instance initializations, generate code
        // for them
        for (JFieldDeclaration staticField : staticFieldInitializations) {
            staticField.codegenInitializations(output);
        }

        // Return
        output.addNoArgInstruction(RETURN);
    }


    @Override
    public void writeToStdOut(PrettyPrinter p) {
        p.printf("<JInterfaceDeclaration line=\"%d\" name=\"%s\"" 
                + " superinterface=\"%s\">\n", line(), name, superInterfaces.toString());
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

        // if(superInterfaces != null) {
        //     p.println("<Extends>");
        //     p.indentRight();
        //     for (Type superType : superInterfaces) {
        //         p.printf("<Extends name=\"%s\"/>\n", superType.toString());
        //     }
        //     p.indentLeft();
        //     p.println("</Extends>");
        // }

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
