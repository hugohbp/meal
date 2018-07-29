/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.senai.estatistica;

import br.com.senai.util.Config;
import br.com.senai.dao.CursoDAO;
import br.com.senai.dao.EscolaDAO;
import br.com.senai.dao.MovimentacaoDAO;
import br.com.senai.entities.Escola;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import javax.swing.AbstractAction;
import javax.swing.ImageIcon;
import javax.swing.Timer;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.CategoryLabelPositions;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.labels.CategorySeriesLabelGenerator;
import org.jfree.chart.labels.ItemLabelAnchor;
import org.jfree.chart.labels.ItemLabelPosition;
import org.jfree.chart.labels.PieSectionLabelGenerator;
import org.jfree.chart.labels.StandardCategoryItemLabelGenerator;
import org.jfree.chart.labels.StandardCategorySeriesLabelGenerator;
import org.jfree.chart.labels.StandardPieSectionLabelGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.DatasetRenderingOrder;
import org.jfree.chart.plot.PiePlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.ValueMarker;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.chart.renderer.category.CategoryItemRenderer;
import org.jfree.chart.renderer.category.LineAndShapeRenderer;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.ui.Align;
import org.jfree.ui.TextAnchor;
import org.jfree.util.Rotation;

/**
 *
 * @author Hugo
 */
public class GerarGrafico {

    public JFreeChart porCursoBarra(Escola escola) {

        DefaultCategoryDataset data = new DefaultCategoryDataset();

        for (Object[] registro : new CursoDAO().quantidadeDeLanches(escola)) {

            data.setValue(Double.parseDouble(registro[1].toString()), registro[0].toString(), registro[0].toString());

        }

        JFreeChart chart = ChartFactory.createBarChart3D("Gráfico de barras \n (qte. de pães p/ curso)", "Cursos da unidade", "Quantidade de lanches diários", data, PlotOrientation.VERTICAL, true, true, true);

        return chart;

    }

    public JFreeChart graficoDefasagem(Escola escola, int mes1, int mes2, int ano) {

        DefaultCategoryDataset dataset1 = new DefaultCategoryDataset();

        for (int i = mes1; i <= mes2; i++) {

            for (Object[] o : new MovimentacaoDAO().totalMovimentacoesRealizadas(Config.usuario, escola, i, ano)) {
                dataset1.addValue((long) o[1], "Nº entrega de lanches", Config.retornaMes(Integer.toString(i)));

            }

        }

        final CategoryItemRenderer renderer = new BarRenderer();
        //    renderer.setLabelGenerator(generator);
        renderer.setBaseItemLabelGenerator(new StandardCategoryItemLabelGenerator());
        renderer.setBaseItemLabelsVisible(true);
        renderer.setBaseSeriesVisible(true);

        final CategoryPlot plot = new CategoryPlot();
        plot.setDataset(dataset1);
        plot.setRenderer(renderer);

        plot.setDomainAxis(new CategoryAxis("Lanches entregues"));
        plot.setRangeAxis(new NumberAxis("Nº máximo de lanches"));

        plot.setOrientation(PlotOrientation.VERTICAL);
        plot.setRangeGridlinesVisible(true);
        plot.setDomainGridlinesVisible(true);

        DefaultCategoryDataset dataset2 = new DefaultCategoryDataset();

        for (int i = mes1; i <= mes2; i++) {

            for (Object[] o : new MovimentacaoDAO().totalMovimentacoes(Config.usuario, escola, i, ano)) {
                dataset2.addValue((long) o[3], "Nº máximo de lanches", Config.retornaMes(Integer.toString(i)));
            }

        }

        final CategoryItemRenderer renderer2 = new LineAndShapeRenderer();

        renderer2.setBaseItemLabelGenerator(new StandardCategoryItemLabelGenerator());
        renderer2.setBaseItemLabelsVisible(true);
        renderer2.setBaseSeriesVisible(true);
        renderer2.setBasePositiveItemLabelPosition(new ItemLabelPosition(ItemLabelAnchor.INSIDE6, TextAnchor.TOP_CENTER));

        plot.setDataset(1, dataset2);
        plot.setRenderer(1, renderer2);

        // change the rendering order so the primary dataset appears "behind" the 
        // other datasets...
        plot.setDatasetRenderingOrder(DatasetRenderingOrder.FORWARD);

        plot.getDomainAxis().setCategoryLabelPositions(CategoryLabelPositions.UP_45);

        final JFreeChart chart = new JFreeChart(plot);
        chart.setTitle("Quantidade de lanches entregues por quantidade de alunos \n" + escola.getNome() + " - " + escola.getCidade().getNome());

        chart.setBackgroundPaint(Config.COR_FUNDO);

//         SimpleDateFormat simpleDateFormat=new SimpleDateFormat("dd/MM/yyyy");
//         SimpleDateFormat simpleDateFormat1=new SimpleDateFormat("MMM");
//         simpleDateFormat.setTimeZone(TimeZone.getDefault());
//         Calendar calendar=Calendar.getInstance();
//        try {
//            calendar.setTime(simpleDateFormat1.parse("01/"+mes1+"/"+ano));
//        } catch (ParseException ex) {
//            Logger.getLogger(GerarGrafico.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        calendar.add(Calendar.MONTH,1);
//        JFreeChart chart = ChartFactory.createBarChart("Defasagem de alunos por mês \n" + escola.getNome(), "Lanches entregues", "Nº máximo de lanches", dataset1, PlotOrientation.VERTICAL, true, true, true);
//        CategoryPlot plot = chart.getCategoryPlot();
//        CategoryAxis domainAxis = plot.getDomainAxis();
//        domainAxis.setCategoryMargin(0.15f);
//        BarRenderer br = new BarRenderer();
//        br.setItemMargin(0.0);
//        chart.getCategoryPlot().setRenderer(br);
//
//        ValueAxis value = plot.getRangeAxis();
//
//        for (Object[] o : new MovimentacaoDAO().totalMovimentacoes(Config.usuario, escola, mes1, ano)) {
//            value.setRange(new Range(0, (long) o[3]));
//        }
        return chart;

    }

    public int angulo = 270;
    PiePlot plot;

    public JFreeChart porCursoPizza(Escola escola) {

        DefaultPieDataset pieDataset = new DefaultPieDataset();

        JFreeChart chart = ChartFactory.createPieChart3D(
                "Gráfico de pizza \n (qte. de pães p/ curso)", // Title
                pieDataset, // Dataset
                true, // Show legend
                true, // Use tooltips
                true); // Configure chart to generate URLs?

        Image image = new ImageIcon("imagens/paes.jpg").getImage();

        chart.setBorderVisible(false);

        chart.setBackgroundImageAlignment(Align.LEFT);

        CategorySeriesLabelGenerator gen1 = new StandardCategorySeriesLabelGenerator("{0}: {1} ({2})");

        PieSectionLabelGenerator gen = new StandardPieSectionLabelGenerator(
                "{0}: {1} ({2})", new DecimalFormat("0"), new DecimalFormat("0%"));

        for (Object[] registro : new CursoDAO().quantidadeDeLanches(escola)) {

            pieDataset.setValue(registro[0].toString(), Double.parseDouble(registro[1].toString()));

        }

        plot = (PiePlot) chart.getPlot();
        plot.setLabelGenerator(gen);
        plot.setBackgroundImage(image);
        plot.setBackgroundImageAlpha(1f);

        mudar(plot);

        return chart;

    }

    public JFreeChart roscaChart() {

        DefaultPieDataset dataset = new DefaultPieDataset();

        JFreeChart chart = ChartFactory.createRingChart("", dataset, true, true, Locale.FRENCH);

        return chart;

    }

    public JFreeChart percentualTurma(Escola escola, int mes, int mes2, int ano) {

//        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
//
//        int i = 1;
//
//        for (int j = mes; j <= mes2; j++) {
//            for (Object[] medias : new MovimentacaoDAO().mediaTurma(escola, j, ano)) {
//
//                System.out.println("Registro " + i + "Nome turma:" + medias[0] + "     Mês:  " + medias[1] + "  Total movimentações: " + (long) medias[2] + "     Dias que pegaram lanche:" + medias[3]);
//                i++;
//
//                double total = Double.parseDouble(medias[2].toString());
//                double dias = Double.parseDouble(medias[3].toString());
//
//                System.out.println(total + "   " + dias);
//                dataset.setValue(total / dias, medias[0].toString(), medias[1].toString());
//            }
//        }
//        
//        JFreeChart chart = ChartFactory.createBarChart("DADOS", "MÊS", "MÉDIAS MENSAIS", dataset, PlotOrientation.VERTICAL, true, true, true);
//
//        CategoryPlot plot = chart.getCategoryPlot();
//        
//
//        CategoryItemRenderer renderer = plot.getRenderer();
//        renderer.setBaseItemLabelGenerator(new StandardCategoryItemLabelGenerator());
//        
//        renderer.setBaseItemLabelsVisible(true);
//        
//        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
//
//        int i = 1;
//
//        for (int j = mes; j <= mes2; j++) {
//            for (Object[] medias : new MovimentacaoDAO().mediaTurma(escola, j, ano)) {
//
//                System.out.println("Registro " + i + "Nome turma:" + medias[0] + "     Mês:  " + medias[1] + "  Total movimentações: " + (long) medias[2] + "     Dias que pegaram lanche:" + medias[3]);
//                i++;
//
//                double total = Double.parseDouble(medias[2].toString());
//                double dias = Double.parseDouble(medias[3].toString());
//
//                System.out.println(total + "   " + dias);
//                dataset.setValue(total / dias, medias[0].toString(), medias[1].toString());
//            }
//        }
//
//        final CategoryItemRenderer renderer = new BarRenderer();
//
//        renderer.setBaseItemLabelsVisible(true);
//
//        final CategoryPlot plot = new CategoryPlot();
//
//        plot.setDataset(dataset);
//        plot.setRenderer(renderer);
//
//        plot.setDomainAxis(new CategoryAxis("Mês referência"));
//        plot.setRangeAxis(new NumberAxis("Valor"));
//
//        plot.setOrientation(PlotOrientation.VERTICAL);
//        plot.setRangeGridlinesVisible(true);
//        plot.setDomainGridlinesVisible(true);
//
//        final ValueAxis rangeAxis2 = new NumberAxis("Média geral da unidade");
//        plot.setRangeAxis(1, rangeAxis2);
//
//
//        DefaultCategoryDataset dataset3 = new DefaultCategoryDataset();
//
        double media = 0.0;
        double total = 0.0;
        double dias = 0.0;
        int quantidade = 0;

        DefaultCategoryDataset dataset1 = new DefaultCategoryDataset();

        List<String> mesesPassados = new ArrayList<>();

        for (int j = mes; j <= mes2; j++) {

            for (Object[] medias : new MovimentacaoDAO().mediaEscola(escola, j, ano)) {

//                System.out.println("Registro " + 0 + "Nome escola:" + medias[0] + "     Mês:  " + medias[1] + "  Total movimentações: " + (long) medias[2] + "     Dias que pegaram lanche:" + medias[3]);
                double total1 = Double.parseDouble(medias[2].toString());
                double dias2 = Double.parseDouble(medias[3].toString());

//                System.out.println(total1 + "   " + dias2);
                dataset1.setValue(total1 / dias2, escola.getNome(), Config.retornaMes(medias[1].toString()));

                mesesPassados.add(medias[1].toString());

            }

        }

        // create the first renderer...
        //      final CategoryLabelGenerator generator = new StandardCategoryLabelGenerator();
        final CategoryItemRenderer renderer = new BarRenderer();
        //    renderer.setLabelGenerator(generator);
        renderer.setBaseItemLabelGenerator(new StandardCategoryItemLabelGenerator());
        renderer.setBaseItemLabelsVisible(true);
        renderer.setBaseSeriesVisible(true);

        final CategoryPlot plot = new CategoryPlot();
        plot.setDataset(dataset1);
        plot.setRenderer(renderer);

        plot.setDomainAxis(new CategoryAxis("Meses"));
        plot.setRangeAxis(new NumberAxis("Quantidade de lanches diários"));

        plot.setOrientation(PlotOrientation.VERTICAL);
        plot.setRangeGridlinesVisible(true);
        plot.setDomainGridlinesVisible(true);

        // now create the second dataset and renderer...
        DefaultCategoryDataset dataset2 = new DefaultCategoryDataset();

        for (int j = mes; j <= mes2; j++) {

            String turma = "";
            String nomeMes = "";
            media = 0.0;
            quantidade = 0;
            for (Escola e3 : new EscolaDAO().escolasPermitidas(Config.usuario)) {

                for (Object[] medias : new MovimentacaoDAO().mediaEscola(e3, j, ano)) {

//                    System.out.println("Registro " + i + "Nome turma:" + medias[0] + "     Mês:  " + medias[1] + "  Total movimentações: " + (long) medias[2] + "     Dias que pegaram lanche:" + medias[3]);
//                    i++;
                    total = Double.parseDouble(medias[2].toString());
                    dias = Double.parseDouble(medias[3].toString());

//                    System.out.println(total + "   " + dias);
                    turma = medias[0].toString();
                    nomeMes = medias[1].toString();
                    media += total / dias;
                    quantidade++;

                }

            }

            if (mesesPassados.indexOf(nomeMes) != -1) {
//                System.out.println(turma + "      mes:" + nomeMes + " media:" + media);

                dataset2.setValue(media / quantidade, "Média geral", Config.retornaMes(nomeMes));
            }

        }

        final CategoryItemRenderer renderer2 = new LineAndShapeRenderer();

        renderer2.setBaseItemLabelGenerator(new StandardCategoryItemLabelGenerator());
        renderer2.setBaseItemLabelsVisible(true);
        renderer2.setBaseSeriesVisible(true);
        renderer2.setBasePositiveItemLabelPosition(new ItemLabelPosition(ItemLabelAnchor.INSIDE6, TextAnchor.TOP_CENTER));

        plot.setDataset(1, dataset2);
        plot.setRenderer(1, renderer2);

        // change the rendering order so the primary dataset appears "behind" the 
        // other datasets...
        plot.setDatasetRenderingOrder(DatasetRenderingOrder.FORWARD);

        plot.getDomainAxis().setCategoryLabelPositions(CategoryLabelPositions.UP_45);

        final JFreeChart chart = new JFreeChart(plot);
        chart.setTitle("Média diária mensal da escola \n" + escola.getNome() + " - " + escola.getCidade().getNome());

        chart.setBackgroundPaint(Config.COR_FUNDO);
        return chart;

    }

    public JFreeChart gerarMediasEscolas(int mes, int ano) {

        double media = 0.0;
        double total = 0.0;
        double dias = 0.0;
        int quantidade = 0;

        DefaultCategoryDataset dataset1 = new DefaultCategoryDataset();

        for (Escola escola1 : new EscolaDAO().escolasPermitidas(Config.usuario)) {

            for (Object[] medias : new MovimentacaoDAO().mediaEscola(escola1, mes, ano)) {

//                System.out.println("Registro " + 0 + "Nome escola:" + medias[0] + "     Mês:  " + medias[1] + "  Total movimentações: " + (long) medias[2] + "     Dias que pegaram lanche:" + medias[3]);
                double total1 = Double.parseDouble(medias[2].toString());
                double dias2 = Double.parseDouble(medias[3].toString());

//                System.out.println(total1 + "   " + dias2);
                dataset1.setValue(total1 / dias2, medias[0].toString(), Config.retornaMes(medias[1].toString()));

            }
        }
        // create the first renderer...
        //      final CategoryLabelGenerator generator = new StandardCategoryLabelGenerator();
        final CategoryItemRenderer renderer = new BarRenderer();
        //    renderer.setLabelGenerator(generator);
        renderer.setBaseItemLabelGenerator(new StandardCategoryItemLabelGenerator());
        renderer.setBaseItemLabelsVisible(true);
        renderer.setBaseSeriesVisible(true);

        final CategoryPlot plot = new CategoryPlot();

        plot.setDataset(dataset1);
        plot.setRenderer(renderer);

        plot.setDomainAxis(new CategoryAxis("Meses"));
        plot.setRangeAxis(new NumberAxis("Quantidade de lanches"));

        plot.setOrientation(PlotOrientation.VERTICAL);
        plot.setRangeGridlinesVisible(true);
        plot.setDomainGridlinesVisible(true);

        // now create the second dataset and renderer...
        DefaultCategoryDataset dataset2 = new DefaultCategoryDataset();

        String turma = "";
        String nomeMes = "";

        for (Escola e3 : new EscolaDAO().escolasPermitidas(Config.usuario)) {

            for (Object[] medias : new MovimentacaoDAO().mediaEscola(e3, mes, ano)) {

//                    System.out.println("Registro " + i + "Nome turma:" + medias[0] + "     Mês:  " + medias[1] + "  Total movimentações: " + (long) medias[2] + "     Dias que pegaram lanche:" + medias[3]);
//                    i++;
                total = Double.parseDouble(medias[2].toString());
                dias = Double.parseDouble(medias[3].toString());

//                    System.out.println(total + "   " + dias);
                turma = medias[0].toString();
                nomeMes = medias[1].toString();
                media += total / dias;
                quantidade++;

            }

        }
        plot.addRangeMarker(new ValueMarker(media / quantidade, Color.BLACK, new BasicStroke(2.0f)));

        if (!nomeMes.equalsIgnoreCase("")) {

//            System.out.println(turma + "      mes:" + nomeMes + " media:" + media);
            dataset2.setValue(media / quantidade, "Média geral", Config.retornaMes(nomeMes));

        }

        final CategoryItemRenderer renderer2 = new LineAndShapeRenderer();

        renderer2.setBaseItemLabelGenerator(new StandardCategoryItemLabelGenerator());
        renderer2.setBaseItemLabelsVisible(true);
        renderer2.setBaseSeriesVisible(true);
        renderer2.setBasePositiveItemLabelPosition(new ItemLabelPosition(ItemLabelAnchor.INSIDE6, TextAnchor.TOP_CENTER));

        plot.setDataset(1, dataset2);
        plot.setRenderer(1, renderer2);

        // change the rendering order so the primary dataset appears "behind" the 
        // other datasets...
        plot.setDatasetRenderingOrder(DatasetRenderingOrder.FORWARD);

        plot.getDomainAxis().setCategoryLabelPositions(CategoryLabelPositions.UP_45);

        final JFreeChart chart = new JFreeChart(plot);
        chart.setTitle("Média das escolas \n (consumos diários de lanche) ");

        chart.setBackgroundPaint(Config.COR_FUNDO);

        return chart;
    }

    public Timer t = new Timer(100, new AbstractAction() {

        @Override
        public void actionPerformed(ActionEvent e) {
            plot.setStartAngle(angulo);
            angulo += 1;
            if (angulo == 360) {
                angulo = 0;
            }

        }
    });

    void mudar(final PiePlot plot) {

        plot.setDirection(Rotation.ANTICLOCKWISE);

        plot.setForegroundAlpha(0.60f);
        plot.setBackgroundPaint(new Color(0, 0, 0, 0));
        plot.setBackgroundAlpha(0.0f);

        t.start();

    }

}
