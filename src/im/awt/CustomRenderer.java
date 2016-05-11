package im.awt;

import java.awt.Color;
import java.awt.Paint;

import org.jfree.chart.renderer.category.BarRenderer;

public class CustomRenderer extends BarRenderer 
{ 
 private Paint[] colors;

 public CustomRenderer() 
 { 
    this.colors = new Paint[] {Color.red, Color.blue, Color.green, 
      Color.yellow, Color.orange, Color.cyan, 
      Color.magenta, Color.blue}; 
 }

 public Paint getItemPaint(final int row, final int column) 
 { 
    // returns color for each column 
    return (this.colors[column % this.colors.length]); 
 } 
}