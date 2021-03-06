/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * ScannerALT.java
 *
 * Created on Jun 30, 2011, 10:05:04 PM
 */

package scanneralt;

import Tab.CloseAndMaxTabbedPane;
import Tab.CloseListener;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.filechooser.FileFilter;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.undo.UndoManager;
import org.antlr.runtime.ANTLRStringStream;
import org.antlr.runtime.CharStream;
import org.antlr.runtime.CommonTokenStream;
import org.antlr.runtime.RecognitionException;
import org.antlr.runtime.TokenStream;
import org.antlr.runtime.tree.CommonTree;

import scan.ScannerALTParser;
import org.antlr.runtime.tree.CommonTreeNodeStream;
import scan.ALTTree;
import scan.Interpreter.BlockNode;
import scan.Interpreter.TLNode;
import scan.ScannerALTLexer;


/**
 *
 * @author katrina
 */
public class Main extends javax.swing.JFrame implements Runnable{

    /**
     * 
     */
    protected String error;
    protected static int lastIndex;
    protected static int fIndex;
    public static boolean debugMode = false;
    public static boolean stepInto = false;
    private static BlockNode block;
    public static Main x;
    Process p;
    /**
     * 
     */
    protected String recovery;
    UndoManager manager;
    
    /** Creates new form ScannerALT */
    public Main() {
        try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnsupportedLookAndFeelException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        initComponents();
        initTab();
//        redirectSystemStreams();

        
            
//        setPane();
 
        setVisible(true);

    }   

    public static String getScan(String id){
        InputDialog in = new InputDialog(null,id);
        in.setVisible(true);
        return in.getInput();
    }
    
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        inputDialog = new javax.swing.JDialog();
        jLabel1 = new javax.swing.JLabel();
        inputField = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jSplitPane1 = new javax.swing.JSplitPane();
        tab = new CloseAndMaxTabbedPane(false);
        jScrollPane2 = new javax.swing.JScrollPane();
        Output = new javax.swing.JEditorPane();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        open = new javax.swing.JMenuItem();
        jSeparator1 = new javax.swing.JPopupMenu.Separator();
        save = new javax.swing.JMenuItem();
        saveAs = new javax.swing.JMenuItem();
        jSeparator2 = new javax.swing.JPopupMenu.Separator();
        exit = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        undo = new javax.swing.JMenuItem();
        redo = new javax.swing.JMenuItem();
        jMenu3 = new javax.swing.JMenu();
        run = new javax.swing.JMenuItem();
        debug = new javax.swing.JMenuItem();
        jMenu4 = new javax.swing.JMenu();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenuItem3 = new javax.swing.JMenuItem();

        jLabel1.setText("Test");

        jButton1.setText("Ok");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout inputDialogLayout = new javax.swing.GroupLayout(inputDialog.getContentPane());
        inputDialog.getContentPane().setLayout(inputDialogLayout);
        inputDialogLayout.setHorizontalGroup(
            inputDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(inputDialogLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(inputDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(inputField, javax.swing.GroupLayout.DEFAULT_SIZE, 380, Short.MAX_VALUE)
                    .addComponent(jLabel1)
                    .addComponent(jButton1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        inputDialogLayout.setVerticalGroup(
            inputDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(inputDialogLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(inputField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jSplitPane1.setDividerSize(3);
        jSplitPane1.setOrientation(javax.swing.JSplitPane.VERTICAL_SPLIT);
        jSplitPane1.setResizeWeight(0.8);

        tab.setTabLayoutPolicy(javax.swing.JTabbedPane.SCROLL_TAB_LAYOUT);
        jSplitPane1.setLeftComponent(tab);

        jScrollPane2.setBackground(new java.awt.Color(204, 204, 204));

        Output.setBackground(new java.awt.Color(204, 204, 204));
        Output.setEditable(false);
        Output.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                OutputKeyTyped(evt);
            }
        });
        jScrollPane2.setViewportView(Output);

        jSplitPane1.setBottomComponent(jScrollPane2);

        jMenu1.setText("File");

        jMenuItem1.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_N, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pics/file-new-icon.png"))); // NOI18N
        jMenuItem1.setText("New");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem1);

        open.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_O, java.awt.event.InputEvent.CTRL_MASK));
        open.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pics/folder-documents-yellow-icon.png"))); // NOI18N
        open.setText("Open");
        open.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                openActionPerformed(evt);
            }
        });
        jMenu1.add(open);
        jMenu1.add(jSeparator1);

        save.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S, java.awt.event.InputEvent.CTRL_MASK));
        save.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pics/floppy-disk-icon.png"))); // NOI18N
        save.setText("Save");
        save.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveActionPerformed(evt);
            }
        });
        jMenu1.add(save);

        saveAs.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S, java.awt.event.InputEvent.SHIFT_MASK | java.awt.event.InputEvent.CTRL_MASK));
        saveAs.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pics/Floppy-3-5-inch-icon.png"))); // NOI18N
        saveAs.setText("Save As");
        saveAs.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveAsActionPerformed(evt);
            }
        });
        jMenu1.add(saveAs);
        jMenu1.add(jSeparator2);

        exit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pics/exit.png"))); // NOI18N
        exit.setText("Exit");
        exit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exitActionPerformed(evt);
            }
        });
        jMenu1.add(exit);

        jMenuBar1.add(jMenu1);

        jMenu2.setText("Edit");

        undo.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_Z, java.awt.event.InputEvent.CTRL_MASK));
        undo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pics/Actions-edit-undo-icon.png"))); // NOI18N
        undo.setText("Undo");
        undo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                undoActionPerformed(evt);
            }
        });
        jMenu2.add(undo);

        redo.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_Y, java.awt.event.InputEvent.CTRL_MASK));
        redo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pics/Actions-edit-redo-icon.png"))); // NOI18N
        redo.setText("Redo");
        redo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                redoActionPerformed(evt);
            }
        });
        jMenu2.add(redo);

        jMenuBar1.add(jMenu2);

        jMenu3.setText("Run");

        run.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F9, 0));
        run.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pics/run-copy-icon 24.png"))); // NOI18N
        run.setText("Run");
        run.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                runActionPerformed(evt);
            }
        });
        jMenu3.add(run);

        debug.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F5, 0));
        debug.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pics/Actions-system-run-icon.png"))); // NOI18N
        debug.setText("Debug");
        debug.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                debugActionPerformed(evt);
            }
        });
        jMenu3.add(debug);

        jMenuBar1.add(jMenu3);

        jMenu4.setText("Next");

        jMenuItem2.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F7, 0));
        jMenuItem2.setText("Next Line");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jMenu4.add(jMenuItem2);

        jMenuItem3.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F4, 0));
        jMenuItem3.setText("Step Into");
        jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem3ActionPerformed(evt);
            }
        });
        jMenu4.add(jMenuItem3);

        jMenuBar1.add(jMenu4);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSplitPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 1001, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSplitPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 433, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void runActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_runActionPerformed
        try
        {
            debugMode = false;
            scan();        // TODO add your handling code here:
        } catch (Exception e) {
            
        }
    }//GEN-LAST:event_runActionPerformed

    private void undoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_undoActionPerformed
        ((InputPane)tab.getSelectedComponent()).undo();
    }//GEN-LAST:event_undoActionPerformed

    private void redoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_redoActionPerformed
        ((InputPane)tab.getSelectedComponent()).redo();   
    }//GEN-LAST:event_redoActionPerformed

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        addTab("Untitled-"+(tab.getTabCount()+1));
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void exitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exitActionPerformed
        System.exit(0);        // TODO add your handling code here:
    }//GEN-LAST:event_exitActionPerformed

    private void openActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_openActionPerformed
        JFileChooser jFileChooser = new JFileChooser();
        FileFilter filter = new FileFilter() {

            @Override
            public boolean accept(File f) {
                if(f.isDirectory())
                    return true;
                
                return f.getName().endsWith(".alt");
            }

            @Override
            public String getDescription() {
                return "ALT Source Code (*.alt)";
            }
        };
        
        jFileChooser.setFileFilter(filter);
        int returnVal = jFileChooser.showOpenDialog(this);
        
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            
            File file = jFileChooser.getSelectedFile();
            
            addTab(file.getName(),file.getPath());
            
            ((InputPane)tab.getComponentAt(tab.getTabCount()-1)).setText(readFile(file));
            
        }
    }//GEN-LAST:event_openActionPerformed

    private void saveAsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveAsActionPerformed
        saveAs();
    }//GEN-LAST:event_saveAsActionPerformed

    private void saveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveActionPerformed
        int tabIndex = tab.getSelectedIndex();
        InputPane p = ((InputPane)tab.getComponentAt(tabIndex));
        
        if(p.hasPath()){
            File f = new File(((InputPane)tab.getSelectedComponent()).getPath());
            save(f,tab.getComponentAt(tabIndex));
        }else
            saveAs();
            
    }//GEN-LAST:event_saveActionPerformed

    private void OutputKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_OutputKeyTyped
        if(evt.getKeyCode() == KeyEvent.VK_ENTER){
            lastIndex = Output.getText().length();

        }
    }//GEN-LAST:event_OutputKeyTyped

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
                // TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed

    private void debugActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_debugActionPerformed
        debugMode = true;    
         p = new Process(Output,((InputPane)tab.getSelectedComponent()));
         new Thread(p).start();
    }//GEN-LAST:event_debugActionPerformed

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
         block.done();
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem3ActionPerformed
        stepInto = true;
        block.done();
    }//GEN-LAST:event_jMenuItem3ActionPerformed
    /**
     * 
     * @param f
     */

    public void saveAs(){
        JFileChooser jFileChooser = new JFileChooser();
        boolean willSave = false;
        FileFilter filter = new FileFilter() {

            @Override
            public boolean accept(File f) {
                if(f.isDirectory())
                    return true;
                
                return f.getName().endsWith(".alt");
            }

            @Override
            public String getDescription() {
                return "ALT Source Code (*.alt)";
            }
        };
        
        jFileChooser.setFileFilter(filter);
        
        int returnVal = jFileChooser.showSaveDialog(this);
            
        if (returnVal == JFileChooser.APPROVE_OPTION){
                        
            File f = jFileChooser.getSelectedFile();
            String name;
            
            if( !f.getPath().endsWith(".alt") ){
                name = f.getPath()+".alt";
                f = new File(name);
            }else{
                name = f.getPath();
            }
            
            
           int tabIndex = tab.getSelectedIndex();
            
            while(f.exists()){
                
               int choice =  JOptionPane.showConfirmDialog(jFileChooser, "File already exists!\nDo you want to Overwrite existing file?", "File Exists!", JOptionPane.YES_NO_CANCEL_OPTION);

               if(choice == JOptionPane.YES_OPTION){
                   willSave = true;
                   break;
               }else if(choice == JOptionPane.NO_OPTION){

                   
                   String[] fName = name.split("\\.");
                   name = "";
                   for(int i = 0; i < fName.length - 1 ; i++){
                       name += fName[i];
                   }
                   name += "(Copy)."+fName[fName.length - 1];
                   f = new File(name);
                   
                   jFileChooser.setSelectedFile(f);
                   jFileChooser.showSaveDialog(this);
                                      
               }
               
               if(choice == JOptionPane.CANCEL_OPTION){
                   willSave = false;
                   break;
               }
            }
            
            if(!f.exists())
               willSave = true;
            
            if(willSave){
                if(save(f,tab.getComponentAt(tabIndex))){
                    tab.setTitleAt(tabIndex, f.getName());
                    ((InputPane)tab.getComponentAt(tabIndex)).setPath(f.getPath());
                }
            }
        }
    }
    public boolean save(File f, Component c){
        boolean success = false;
        
        try {
                 
            String fileName = f.getPath();
            
            if(f.exists())
                f.delete();
            
            f = new File(fileName);
            f.createNewFile();

            FileWriter output = new FileWriter(f);
            output.write(((InputPane)c).getText());
            output.flush();
            output.close();

        } catch (IOException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        } finally{
            success = true;
        }
        
        return success;
    }
    
    
    public void addTab(String name){
        tab.addTab(name, new InputPane());
    }
    
    public void addTab(String name,String path){
        tab.addTab(name, new InputPane(path));
    }
    /**
    * @param args the command line arguments
    */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
               x = new Main();

//                System.out.println("Input: ");
//                Scanner input = new Scanner(System.in);
//                String str = input.nextLine();
//                CharStream s = new ANTLRStringStream(str);
//                scan.ScannerALT sc = new scan.ScannerALT(s);
//        while ( s.index() < s.size() ) {
//            Token t = sc.nextToken();
//            String text = t.getText();
//            int type = t.getType();
//            System.out.println("Token: "+text+ "\nType: " +type+ "\n");
//        }

            }
        });
    }
    
    /**
     * 
     */
    public void setPane(){
//
//        TextLineNumber lNum = new TextLineNumber(Input);
//        jScrollPane1.setRowHeaderView(lNum);
//        manager = new UndoManager();
//        
//        Input.getDocument().addUndoableEditListener(new UndoableEditListener() {
//                        public void undoableEditHappened(UndoableEditEvent e) {
//                                manager.addEdit(e.getEdit());
//                        }
//                });
//        
//        Input.requestFocus();
//        Input.setSize(jPanel1.getWidth(), jPanel1.getHeight());
//        jSplitPane1.setDividerLocation(.8);  
////        jTabbedPane1.setEnabledAt(0, false);
          
    }
    
    /**
     * 
     */
    public static void scan() throws RecognitionException{
        // TODO add your handling code here:
        Output.setText(null);
        //error = null;
        //recovery = null;
        //System.out.println(Output.getText());
        CharStream charStream = new ANTLRStringStream(((InputPane)tab.getSelectedComponent()).getText());
	ScannerALTLexer lexer = new ScannerALTLexer(charStream);
	TokenStream tokenStream = new CommonTokenStream(lexer);
	ScannerALTParser parser = new ScannerALTParser(tokenStream);
        
        CommonTree tree = (CommonTree)parser.parse().getTree();
        CommonTreeNodeStream nodes = new CommonTreeNodeStream(tree);
//        parse_return program = parser.parse();
//        Output.setText(Output.getText()+ program.tree.toStringTree() + "\n");
//	System.out.println(((CommonTree)program.tree).toStringTree());
        // pass the reference to the Map of functions to the tree walker
        ALTTree walker = new ALTTree(nodes, parser.functions);

        // get the returned node 
        TLNode returned = walker.walk();
        returned.evaluate();
	

        try {
            
//            CommonTreeNodeStream nodeStream = new CommonTreeNodeStream(program.tree);
//            ALTTree walker = new ALTTree(nodeStream,parser.functions);            
            //            System.out.println(sParse.getCurrentInputSymbol(sParse.getTokenStream()).toString());
            int i = 0;

            // get the returned node 
 
//            System.out.println(returned == null ? "null" : returned.evaluate());
            
            
//            for(i = 0; i < parser.getErrors().size(); i++) {
//                Output.setText(Output.getText()+parser.getErrors().get(i) + "\n");
//            }
//
//            Output.setText(Output.getText() + "result = " + walker.evaluator() + "\n");
            
        } catch (Exception ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }

	
    
        
        //
        //        while ( s.index() < s.size() ) {
        //            List a = sc.getNextToken();
        //            Token t = (Token)a.get(0);
        //            error = (String)a.get(1);
        //            recovery = (String)a.get(2);
        //            String text = t.getText();
        //            int type = t.getType();
        //            //System.out.println(t);
        //
        //            if (type == scan.ScannerALT.WS)
        //                continue;
        //
        //            String tType;
        //
        //            switch(type){
        //                case scan.ScannerALT.DATATYPE:tType = "Data Type";break;
        //                case scan.ScannerALT.RETURNTYPE:tType = "Return Type";break;
        //                case scan.ScannerALT.STRING_LITERAL:tType = "String Literal";break;
        //                case scan.ScannerALT.CHAR_LITERAL:tType = "Character Literal";break;
        //                case scan.ScannerALT.LETTER:tType = "Letter";break;
        //                case scan.ScannerALT.COMPAREOPS:tType = "Compare Operator";break;
        //                case scan.ScannerALT.LOGICALOPS:tType = "Logical Operator";break;
        //                case scan.ScannerALT.MATHOPS:tType = "Math Operator";break;
        //                case scan.ScannerALT.DIGIT:tType = "Digit";break;
        //                case scan.ScannerALT.INTEGER:tType = "Integer";break;
        //                case scan.ScannerALT.EXPONENT:tType = "Exponent";break;
        //                case scan.ScannerALT.FLOAT:tType = "Float";break;
        //                case scan.ScannerALT.ID:tType = "Identifier";break;
        //                case scan.ScannerALT.ARRAYID:tType = "Array Identifier";break;
        //                case scan.ScannerALT.ASSIGN:tType = "Assignment";break;
        //                case scan.ScannerALT.SEMICOLON:tType = "Semicolon";break;
        //                case scan.ScannerALT.COMMA:tType = "Comma";break;
        //                case scan.ScannerALT.LEFTPAREN:tType = "Left Parenthesis";break;
        //                case scan.ScannerALT.RIGHTPAREN:tType = "Right Parenthesis";break;
        //                case scan.ScannerALT.LEFTCURLY:tType = "Left Curly Braces";break;
        //                case scan.ScannerALT.RIGHTCURLY:tType = "Right Curly Braces";break;
        //                case scan.ScannerALT.WS:tType = "White Space";break;
        //                case scan.ScannerALT.COMMENT:tType = "Comment";break;
        //                case scan.ScannerALT.MULTILINE_COMMENT:tType = "Multi-Line Comment";break;
        //                //case scan.ScannerALT.DEFAULT:tType = "Unidentified";break;
        //                default:tType = "Unidentified";break;
        //            }
        //            //System.out.println(sc.getErrorMessage(null, sc.getTokenNames()));
        //            //System.out.println(sc.getTokenErrorDisplay(t));
        //
        //            int line = t.getLine();
        //            int column = t.getCharPositionInLine() + 1;
        //
        //            if(!(error == null))
        //            {
        //                Output.append("Error found in line " + line + " column " + column + ": " + error + "\n");
        //                Output.append(recovery + " at line "+line+" column "+column+"\n");
        //            }
        //            else
        //        }
        //        }
    }
    
    public static void setEditable(boolean a){
        Output.setEditable(a);
        String s = Output.getText();
        if(a)
            fIndex = s.length();
                 
    }
    
    public static void runThrough(){
        ((InputPane)tab.getSelectedComponent()).initCompletion();
        
        try {
            CharStream charStream = new ANTLRStringStream(((InputPane)tab.getSelectedComponent()).getText());
            ScannerALTLexer lexer = new ScannerALTLexer(charStream);
            TokenStream tokenStream = new CommonTokenStream(lexer);
            ScannerALTParser parser = new ScannerALTParser(tokenStream);
            
            parser.parse();
        } catch (RecognitionException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    public static InputPane getSelTab(){
        return ((InputPane)tab.getSelectedComponent());
    }
    
    
    public void initTab(){
//        addTab("Untitled-1");
        
        ((CloseAndMaxTabbedPane)tab).addCloseListener(new CloseListener() {
                public void closeOperation(MouseEvent e) {
                    tab.remove(((CloseAndMaxTabbedPane)tab).getOverTabIndex());
                }
                public void actionPerformed(ActionEvent e) {
                    throw new UnsupportedOperationException("Not supported yet.");
                }
            });
    }
    
    public String readFile(File file){
        
        String strLine = null;
        String content = "";
        try{
            // Open the file that is the first 
            // command line parameter
            FileInputStream fstream = new FileInputStream(file);
            // Get the object of DataInputStream
            DataInputStream in = new DataInputStream(fstream);
                BufferedReader br = new BufferedReader(new InputStreamReader(in));
            
                     
            while((strLine = br.readLine()) != null)   {
                content += strLine+"\n";
            }
            
            //Close the input stream
            in.close();
            
        }catch (Exception e){//Catch exception if any
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, e);
        }
        return content;
    }
    
   
    /**
     * 
     * @param a
     */
    public void setOutput(Exception a)
    {
        Output.setText(Output.getText()+ a.toString());
    }
    public static void setOutput(String a)
    {
        Output.setText(Output.getText()+ a+"\n");
        System.out.println(a);
    }
    /**
     * 
     * @param s
     * @param a
     */
    public static void setDetected(String s, String a){
     
        System.out.println(a);
        String b[] = a.split(",");

        String c[] = b[3].split("]");

        String d[] = c[0].split(":");

        Output.setText(Output.getText()+s+ " "+ d[0] +"\n");
    }

        private void updateTextPane(final String text) {  
          SwingUtilities.invokeLater(new Runnable() {  
            public void run() {  
              Document doc = Output.getDocument();  
              try {  
                doc.insertString(doc.getLength(), text, null);  
              } catch (BadLocationException e) {  
                throw new RuntimeException(e);  
              }  
              Output.setCaretPosition(doc.getLength() - 1);  
            }  
          });  
        }  

        private void redirectSystemStreams() {  
          OutputStream out = new OutputStream() {  
            @Override  
            public void write(final int b) throws IOException {  
              updateTextPane(String.valueOf((char) b));  
            }  

            @Override  
            public void write(byte[] b, int off, int len) throws IOException {  
              updateTextPane(new String(b, off, len));  
            }  

            @Override  
            public void write(byte[] b) throws IOException {  
              write(b, 0, b.length);  
            }  
          };  

          System.setOut(new PrintStream(out, true));  
          System.setErr(new PrintStream(out, true));  
        }  
    
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private static javax.swing.JEditorPane Output;
    private javax.swing.JMenuItem debug;
    private javax.swing.JMenuItem exit;
    private static javax.swing.JDialog inputDialog;
    public static javax.swing.JTextField inputField;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JPopupMenu.Separator jSeparator1;
    private javax.swing.JPopupMenu.Separator jSeparator2;
    private javax.swing.JSplitPane jSplitPane1;
    private javax.swing.JMenuItem open;
    private javax.swing.JMenuItem redo;
    private javax.swing.JMenuItem run;
    private javax.swing.JMenuItem save;
    private javax.swing.JMenuItem saveAs;
    private static javax.swing.JTabbedPane tab;
    private javax.swing.JMenuItem undo;
    // End of variables declaration//GEN-END:variables

    public void run() {
        try {
            scan();
        } catch (RecognitionException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * @param block the block to set
     */
    public static void setBlock(BlockNode bl) {
        block = bl;
    }

  

}
