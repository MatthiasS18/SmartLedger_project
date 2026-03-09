import { Component, Input, OnChanges } from '@angular/core';
import { NgApexchartsModule } from 'ng-apexcharts';
import { ApexNonAxisChartSeries, ApexChart, ApexLegend, ApexTooltip } from 'ng-apexcharts';
import { ExpenseDto } from '../../page/expenses/Expenses.component';

@Component({
  selector: 'app-donut-chart',
  standalone: true,
  imports: [NgApexchartsModule],
  templateUrl: './DonutChart.component.html'
})


export class DonutChart implements OnChanges {
  @Input() expenses: ExpenseDto[] = [];

  series: ApexNonAxisChartSeries = [];
  labels: string[] = [];

  chart: ApexChart = {
    type: 'donut',
    width: '100%',
    height: 350,
  };

  legend: ApexLegend = {
    position: 'bottom',
    labels: { colors: '#e8e8f0' }
  };

  tooltip: ApexTooltip = { theme: 'dark' };

  ngOnChanges() {
    this.buildChart();
  }

  buildChart() {
    const map = new Map<string, number>();
    this.expenses.forEach(e => {
      const current = map.get(e.category) || 0;
      map.set(e.category, current + e.amount);
    });

    this.labels = Array.from(map.keys());
    this.series = Array.from(map.values());
  }
}