/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.ArrayList;
import javax.swing.JFrame;
//import org.jfree.chart.ChartFactory;
//import org.jfree.chart.ChartPanel;
//import org.jfree.chart.JFreeChart;
//import org.jfree.chart.plot.PlotOrientation;
//import org.jfree.data.general.DefaultPieDataset;
//import org.jfree.data.general.PieDataset;
//import org.jfree.ui.ApplicationFrame;
//import org.jfree.data.category.DefaultCategoryDataset;

public class CalculoEstatistica {
//   
//    public DefaultCategoryDataset carregaLinha(ArrayList<Cotacao> empresaLista){
//        float media = 0;
//        float min = 0 , max = 0;
//        DefaultCategoryDataset result = new DefaultCategoryDataset();
//        JFrame jf = new JFrame();
//        jf.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
//        jf.setSize( 500, 500 );
//        DefaultCategoryDataset ds = new DefaultCategoryDataset();
//        
//        for(int i = 0 ; i < empresaLista.size() ; i++){
//            min = empresaLista.get(i).getMinima();
//            max = empresaLista.get(i).getMaxima();
//            ds.addValue( ((min + max) / 2), "Média cotação", (Comparable) i );
//        }
//        return ds;
//    }
//    
//    public PieDataset carregarVariacao(ArrayList<Cotacao> cotacaoLista) {
//		// Possui 180 itens na lista
//                int num_dias_1 = 0, num_dias_2 = 0, num_dias_3 = 0, num_dias_4 = 0;
//                int num_dias_5 = 0, num_dias_6 = 0, num_dias_7 = 0, num_dias_8 = 0,num_dias_9 = 0;
//                float soma1 = 0,soma2 = 0, soma3 = 0, soma4 = 0;
//                float soma5 = 0,soma6 = 0, soma7 = 0, soma8 = 0, soma9 = 0;
//                double valor1 = 0;  // Ambev
//                double valor2 = 0;  // Mag Luiza
//                String nome_empresa;
//                
//                for(int i = 0 ; i < cotacaoLista.size() ; i++){
//
//                    if( cotacaoLista.get(i).getEmpresa().equals("AMBEV ON")){
//                        num_dias_1++;
//                        soma2 = soma2 + cotacaoLista.get(i).getVariacao();
//                    }
//                    else if( cotacaoLista.get(i).getEmpresa().equals("BAHEMA ON")){
//                        num_dias_2++;
//                        soma2 = soma2 + cotacaoLista.get(i).getVariacao();
//                    }  else if( cotacaoLista.get(i).getEmpresa().equals("BANRISUL ON")){
//                        num_dias_3++;
//                        soma3 = soma3 + cotacaoLista.get(i).getVariacao();
//                    }  else if( cotacaoLista.get(i).getEmpresa().equals("CIELO ON")){
//                        num_dias_2++;
//                        soma4 = soma4 + cotacaoLista.get(i).getVariacao();
//                    }  else if( cotacaoLista.get(i).getEmpresa().equals("ITAUUNIBANCO ON")){
//                        num_dias_5++;
//                        soma5 = soma5 + cotacaoLista.get(i).getVariacao();
//                    }  else if( cotacaoLista.get(i).getEmpresa().equals("MAG LUIZA ON")){
//                        num_dias_6++;
//                        soma6 = soma6 + cotacaoLista.get(i).getVariacao();
//                    }  else if( cotacaoLista.get(i).getEmpresa().equals("PETROBRAS PN")){
//                        num_dias_7++;
//                        soma7 = soma7 + cotacaoLista.get(i).getVariacao();
//                    }  else if( cotacaoLista.get(i).getEmpresa().equals("SANTANDER BR ON")){
//                        num_dias_8++;
//                        soma8 = soma8 + cotacaoLista.get(i).getVariacao();
//                    }  else if( cotacaoLista.get(i).getEmpresa().equals("VALE ON")){
//                        num_dias_9++;
//                        soma9 = soma9 + cotacaoLista.get(i).getVariacao();
//                    }                       
//                    
//                }
// 
//		DefaultPieDataset result = new DefaultPieDataset();
//		result.setValue("AMBEV ON", soma1 / num_dias_1);
//		result.setValue("BAHEMA ON", soma2 / num_dias_2);
//		result.setValue("BANRISUL ON", soma3 / num_dias_3);
//                result.setValue("CIELO ON", soma4 / num_dias_4);
//                result.setValue("ITAUUNIBANCO ON",soma5 / num_dias_5);
//                result.setValue("MAG LUIZA ON",soma6 / num_dias_6);
//                result.setValue("PETROBRAS PN",soma7 / num_dias_7);
//                result.setValue("SANTANDER BR ON",soma8/ num_dias_8);
//                result.setValue("VALE ON",soma9 / num_dias_9);
//		return result;
//	}
}
