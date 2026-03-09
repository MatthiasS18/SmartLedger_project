import { Component, Input, OnChanges } from '@angular/core';
import { NgApexchartsModule } from 'ng-apexcharts';
import {
  ApexAxisChartSeries,
  ApexChart,
  ApexXAxis,
  ApexYAxis,
  ApexTooltip,
  ApexPlotOptions
} from 'ng-apexcharts';
import { InvoiceDto } from '../../page/invoices/Invoices.component';

@Component({
  selector: 'app-invoice-chart',
  standalone: true,
  imports: [NgApexchartsModule],
  templateUrl: './BarChart.component.html',
})
export class InvoiceBarChart implements OnChanges {
  @Input() invoices: InvoiceDto[] = [];

  series: ApexAxisChartSeries = [];
  
  chart: ApexChart = {
    type: 'bar',
    width: '100%',
    height: 350,
    foreColor: '#e8e8f0'
  };

  xaxis: ApexXAxis = { categories: [] };
  
  yaxis: ApexYAxis = {
    labels: { style: { colors: '#e8e8f0' } }
  };

  tooltip: ApexTooltip = { theme: 'dark' };

  plotOptions: ApexPlotOptions = {
    bar: { borderRadius: 6, columnWidth: '50%' }
  };

  ngOnChanges() {
    this.buildChart();
  }

  buildChart() {
    const map = new Map<string, number>();
    this.invoices.forEach(inv => {
      if (!inv.issueDate) return;
      const month = inv.issueDate.substring(0, 7);
      const current = map.get(month) || 0;
      map.set(month, current + inv.totalTTC);
    });

    const sorted = Array.from(map.entries()).sort((a, b) => a[0].localeCompare(b[0]));

    this.xaxis = { 
      categories: sorted.map(e => e[0]),
      labels: { style: { colors: '#e8e8f0' } }
    };
    this.series = [{ name: 'Total TTC', data: sorted.map(e => e[1]) }];
  }
}