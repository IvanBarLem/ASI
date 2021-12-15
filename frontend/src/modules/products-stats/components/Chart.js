import React, { useState } from "react";
import { PieChart, Pie, Sector } from "recharts";

export default function Chart(props) {
  const { productName, kpi, otherKpi, otherName, unid } = props;

  const data = [
    { id: 1, name: productName, value: kpi },
    { id: 2, name: otherName, value: otherKpi },
  ];

  const renderActiveShape = (props) => {
    const RADIAN = Math.PI / 180;
    const {
      cx,
      cy,
      midAngle,
      innerRadius,
      outerRadius,
      startAngle,
      endAngle,
      payload,
      value,
    } = props;
    const sin = Math.sin(-RADIAN * midAngle);
    const cos = Math.cos(-RADIAN * midAngle);
    const sx = cx + (outerRadius + 10) * cos;
    const sy = cy + (outerRadius + 10) * sin;
    const mx = cx + (outerRadius + 10) * cos;
    const my = cy + (outerRadius + 20) * sin;
    const ex = mx + (cos >= 0 ? 1 : -1) * 22;
    const ey = my;
    const textAnchor = cos >= 0 ? "start" : "end";

    return (
      <g>
        <text x={cx} y={cy} dy={-90} textAnchor="middle" fill="#42A5F5">
          {payload.name}
        </text>
        <Sector
          cx={cx}
          cy={cy}
          innerRadius={innerRadius - 5}
          outerRadius={outerRadius + 5}
          startAngle={startAngle}
          endAngle={endAngle}
          fill="#008000"
        />
        <Sector
          cx={cx}
          cy={cy}
          startAngle={startAngle}
          endAngle={endAngle}
          innerRadius={outerRadius + 6}
          outerRadius={outerRadius + 10}
          fill="#42A5F5"
        />
        <path
          d={`M${sx},${sy}L${mx},${my}L${ex},${ey}`}
          stroke="#42A5F5"
          fill="none"
        />
        <circle cx={ex} cy={ey} r={2} fill="#42A5F5" stroke="none" />
        <text
          x={ex + (cos >= 0 ? 1 : -1) * 12}
          y={ey}
          textAnchor={textAnchor}
          fill="#42A5F5"
        >
          {value}
          {"  "}
          {unid}
        </text>
        <text
          x={ex + (cos >= 0 ? 1 : -1) * 12}
          y={ey}
          dy={18}
          textAnchor={textAnchor}
          fill="#42A5F5"
        >
          {`(KPI ${
            payload.id === 1
              ? ((kpi / otherKpi) * 100).toFixed(2)
              : (100 - (kpi / otherKpi) * 100).toFixed(2)
          }%)`}
        </text>
      </g>
    );
  };

  const [activeIndex, setActiveIndex] = useState(0);

  const onPieEnter = (_, index) => {
    setActiveIndex(index);
  };

  return (
    <PieChart width={350} height={200}>
      <Pie
        style={{ marginInline: "auto" }}
        activeIndex={activeIndex}
        activeShape={renderActiveShape}
        data={data}
        cx={175}
        cy={100}
        innerRadius={30}
        outerRadius={40}
        fill="#cb3234"
        dataKey="value"
        onMouseEnter={onPieEnter}
        onMouseLeave={() => setActiveIndex(0)}
      />
    </PieChart>
  );
}
