package ui;

import com.jogamp.opengl.*;
import com.jogamp.opengl.awt.GLCanvas;
import com.jogamp.opengl.glu.GLU;
import com.jogamp.opengl.glu.GLUquadric;
import com.jogamp.opengl.util.FPSAnimator;

/**
 * The canvas on which the user can draw molecules.
 * @author Maria Stephenson
 */
public class MoleculeDrawingCanvas extends GLCanvas implements GLEventListener {

    private FPSAnimator animator;
    private GLU glu;

    /**
     * Constructor
     * @param capabilities the minimum capability requirements
     * @param width the width of the canvas
     * @param height the height of the canvas
     */
   public MoleculeDrawingCanvas(GLCapabilities capabilities, int width, int height) {
       super(capabilities);
       setSize(width, height);
       addGLEventListener(this);
   }

    /**
     * Sets up the animator, glu and shading/drawing specifications
     */
    @Override
    public void init(GLAutoDrawable drawable) {
        GL2 gl = drawable.getGL().getGL2();

        gl.glEnable(GL.GL_DEPTH_TEST);
        gl.glDepthFunc(GL.GL_LEQUAL);
        gl.glShadeModel(GL2.GL_SMOOTH);
        gl.glHint(GL2.GL_PERSPECTIVE_CORRECTION_HINT, GL2.GL_NICEST);
        animator = new FPSAnimator(60);
        animator.start();

        glu = new GLU();
    }

    @Override
    public void dispose(GLAutoDrawable drawable) {

    }

    /**
     * Draws a sphere to the screen
     */
    @Override
    public void display(GLAutoDrawable drawable) {
        GL2 gl = drawable.getGL().getGL2();
        gl.glClear(GL.GL_COLOR_BUFFER_BIT | GL.GL_DEPTH_BUFFER_BIT);
        setCamera(gl, glu, 100);

        float SHINE_ALL_DIRECTIONS = 1;
        float[] lightPos = {-2000, -1500, 0, SHINE_ALL_DIRECTIONS};
        float[] lightColorAmbient = {0.4f, 0.4f, 0.4f, 1f};
        float[] lightColorSpecular = {0.3f, 0.3f, 0.3f, 1f};
        
        gl.glLightfv(GL2.GL_LIGHT1, GL2.GL_POSITION, lightPos, 0);
        gl.glLightfv(GL2.GL_LIGHT1, GL2.GL_AMBIENT, lightColorAmbient, 0);
        gl.glLightfv(GL2.GL_LIGHT1, GL2.GL_SPECULAR, lightColorSpecular, 0);
        
        gl.glEnable(GL2.GL_LIGHT1);
        gl.glEnable(GL2.GL_LIGHTING);
        
        float[] rgba = {0.3f, 0.5f, 1f};
        gl.glMaterialfv(GL.GL_FRONT, GL2.GL_AMBIENT, rgba, 0);
        gl.glMaterialfv(GL.GL_FRONT, GL2.GL_SPECULAR, rgba, 0);
        gl.glMaterialf(GL.GL_FRONT, GL2.GL_SHININESS, 0.5f);


        gl.glColor3f(0.3f, 0.5f, 1f);
        GLUquadric molecule = glu.gluNewQuadric();
        glu.gluQuadricDrawStyle(molecule, GLU.GLU_FILL);
        glu.gluQuadricNormals(molecule, GLU.GLU_FLAT);
        glu.gluQuadricOrientation(molecule, GLU.GLU_OUTSIDE);
        final float radius = 2;
        final int slices = 20;
        final int stacks = 20;
        glu.gluSphere(molecule, radius, slices, stacks);
        glu.gluDeleteQuadric(molecule);
    }

    /**
     * Sets up the camera
     * @param distance the distance the camera is
     */
    private void setCamera(GL2 gl, GLU glu, float distance) {
        gl.glMatrixMode(GL2.GL_PROJECTION);
        gl.glLoadIdentity();

        float widthHeightRatio = (float) getWidth() / (float) getHeight();
        glu.gluPerspective(45, widthHeightRatio, 1, 1000);
        glu.gluLookAt(0, 0, distance, 0, 0,0 , 0, 1, 0);

        gl.glMatrixMode(GL2.GL_MODELVIEW);
        gl.glLoadIdentity();
    }

    @Override
    public void reshape(GLAutoDrawable drawable, int x, int y, int width, int height) {

    }
}
