/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DBAcces;

import DataAccess.MaterialMapper;
import FunctionLayer.FogExceptions.FogLoginException;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import java.util.ArrayList;
import FunctionLayer.partslist.Material;
import FunctionLayer.partslist.Wood;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Esben
 */
public class MaterialMapperTest {
    Material m1;
    public MaterialMapperTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
        Material m1 = new Material(200, "timber",12.33,22,22);
    }
    
    @Test
    public void testAddMatToDB() throws FogLoginException{
        MaterialMapper mm = new MaterialMapper();
//        mm.addMatToDB(m1);
//        assertEquals(1,1);
    }
    
}
