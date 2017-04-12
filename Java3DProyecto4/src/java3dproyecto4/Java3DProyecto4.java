/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package java3dproyecto4;
import com.sun.j3d.utils.universe.SimpleUniverse;
import com.sun.j3d.utils.geometry.ColorCube;
import com.sun.j3d.utils.geometry.Sphere;
import com.sun.j3d.utils.image.TextureLoader;
import java.awt.Container;
import javax.media.j3d.AmbientLight;
import javax.media.j3d.Appearance;
import javax.media.j3d.BoundingSphere;
import javax.media.j3d.BranchGroup;
import javax.media.j3d.DirectionalLight;
import javax.media.j3d.Material;
import javax.media.j3d.Texture;
import javax.media.j3d.TextureAttributes;
import javax.media.j3d.Transform3D;
import javax.media.j3d.TransformGroup;
import javax.vecmath.Color3f;
import javax.vecmath.Color4f;
import javax.vecmath.Point3d;
import javax.vecmath.Vector3f;
/**
 *
 * @author Juan
 */
public class Java3DProyecto4 {

     public Java3DProyecto4(){
        SimpleUniverse escena = new SimpleUniverse(); // Voy a hacer una escena
        BranchGroup rama = new BranchGroup(); // Voy a hacer una jerarquia
        
        
        Color3f coldifuso = new Color3f(1.0f,1.0f,1.0f);
        Color3f colambiental = new Color3f(0.0f,0.0f,0.0f);
        Color3f colemisivo = new Color3f(0.0f,0.0f,0.0f);
        Color3f colespecular = new Color3f(1.0f,1.0f,1.0f);
        float brillo = 1.0f;
        Appearance miapariencia = new Appearance();
        
        
        // Lineas especificas para cargar una textura
        TextureLoader cargador = new TextureLoader("D:\\JoseVicente\\tex\\agua.jpg","DIFFUSE", new Container());
        Texture textura = cargador.getTexture(); // Crea una textura y le aplicas la textura del cargador
        textura.setBoundaryModeS(Texture.WRAP); //  El tipo de repeticion de la textura en X
        textura.setBoundaryModeT(Texture.WRAP); //  El tipo de repeticion para la textura en Y
        textura.setBoundaryColor( new Color4f( 0.0f, 1.0f, 0.0f, 0.0f ) ); // Color que representar en el caso de que la textura sea transparente
        
        TextureAttributes texAttr = new TextureAttributes();    // 
        texAttr.setTextureMode(TextureAttributes.MODULATE);
        miapariencia.setTexture(textura);
        miapariencia.setTextureAttributes(texAttr);
// Lineas especificas para cargar una textura
        miapariencia.setMaterial(new Material(colambiental,colemisivo,coldifuso,colespecular,brillo));
        
        int banderas = 3;
        int divisiones = 36;
        float radio = 0.5f;
        Sphere miesfera = new Sphere(radio,banderas,divisiones,miapariencia);
        
        TransformGroup grupotransformacion = new TransformGroup(); // Preparo un grupo de transformaciones
        Transform3D mitransformacion = new Transform3D(); // Preparo una transformacion
        Vector3f mivector = new Vector3f(0.0f,0.0f,0.0f); // Creo un vector tridimensional
        mitransformacion.setTranslation(mivector); // Ok, transformo con el vector
        grupotransformacion.setTransform(mitransformacion);
        grupotransformacion.addChild(miesfera);
        
        rama.addChild(grupotransformacion); // A la jerarquia le añado una rama
        // Voy a crear una luz
        Color3f colorluz = new Color3f(0.5f,0.5f,0.5f); // Creo un color de luz
        Vector3f direccionluz = new Vector3f(-1.0f,-1.0f,0.0f); // Direccion de la luz
        DirectionalLight luz1 = new DirectionalLight(colorluz,direccionluz); // Creo una instancia de la luz
        BoundingSphere alcance = new BoundingSphere(new Point3d(0.0,0.0,0.0),100); // El alcance que abarca la luz direccion
        luz1.setInfluencingBounds(alcance); // Le digo a la luz exactamente cual es su alcance
        rama.addChild(luz1);
        
        // Voy a crear una luz
        Color3f colorluz2 = new Color3f(0.5f,0.5f,0.5f); // Creo un color de luz
        Vector3f direccionluz2 = new Vector3f(1.0f,-1.0f,0.0f); // Direccion de la luz
        DirectionalLight luz2 = new DirectionalLight(colorluz2,direccionluz2); // Creo una instancia de la luz
        BoundingSphere alcance2 = new BoundingSphere(new Point3d(0.0,0.0,0.0),100); // El alcance que abarca la luz direccion
        luz2.setInfluencingBounds(alcance2); // Le digo a la luz exactamente cual es su alcance
        rama.addChild(luz2);
        
        Color3f colorambiente = new Color3f(255.0f,255.0f,255.0f);
        AmbientLight luzambiente = new AmbientLight(colorambiente);
        BoundingSphere alcance3 = new BoundingSphere(new Point3d(0.0,0.0,0.0),1000); // El alcance que abarca la luz direccion
        luzambiente.setInfluencingBounds(alcance3); // Le digo a la luz exactamente cual es su alcance
        rama.addChild(luzambiente);
        
        
       
        escena.getViewingPlatform().setNominalViewingTransform(); // Dame las coordenadas de transformacion de la plataforma actual
        escena.addBranchGraph(rama); // A la escena le añado la caja
    }
    public static void main(String[] args) {
        new Java3DProyecto4();
    }
    
}
