<%@ page import="org.codehaus.jackson.map.ObjectMapper" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="com.gt.bmf.pojo.GuDong" %>
<%@ page import="java.util.List" %>
<%@ page import="org.apache.commons.lang.ArrayUtils" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="org.apache.commons.lang.time.DateUtils" %>
<%@ page import="java.util.Date" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html class="no-js" lang="en">
<head>
    <title>LKK</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <meta name="description" content="" />
    <%@ include file="/include.inc.jsp"%>
    <%@ include file="/include.js.jsp"%>
    <style>
        table {
            width: 60%;
            font-size: 24px;
        }
    </style>
    <script type="text/javascript" src="http://cdn.hcharts.cn/highcharts/highcharts.js"></script>
    <script type="text/javascript" src="http://cdn.hcharts.cn/highcharts/exporting.js"></script>
    <script>
        <%
         ObjectMapper objectMapper = new ObjectMapper();
       //objectMapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd"));
     //  String json = objectMapper.writeValueAsString(request.getAttribute("list"));
       //out.println(json);

        Date stand  = DateUtils.addYears(new Date(),-2);
        List<GuDong> list =(List<GuDong>)request.getAttribute("list");
        List<String> categories = new ArrayList<String>();
        List<Integer> counts = new ArrayList<Integer>();
        List<Double> prices = new ArrayList<Double>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        for(GuDong obj : list){
            if(obj.getDate().before(stand)){
               break;
            }
            categories.add(sdf.format(obj.getDate()));
            counts.add(obj.getMarkCount());
            prices.add(obj.getPrice());
        }
        request.setAttribute("name",list.get(0).getName());
        request.setAttribute("code",list.get(0).getCode());

       %>
        Highcharts.theme = {
            colors: ['#058DC7', '#50B432', '#ED561B', '#DDDF00', '#24CBE5', '#64E572', '#FF9655', '#FFF263', '#6AF9C4'],
            chart: {
                backgroundColor: {
                    linearGradient: { x1: 0, y1: 0, x2: 1, y2: 1 },
                    stops: [
                        [0, 'rgb(255, 255, 255)'],
                        [1, 'rgb(240, 240, 255)']
                    ]
                },
                borderWidth: 2,
                plotBackgroundColor: 'rgba(255, 255, 255, .9)',
                plotShadow: true,
                plotBorderWidth: 1
            },
            title: {
                style: {
                    color: '#000',
                    font: 'bold 16px "Trebuchet MS", Verdana, sans-serif'
                }
            },
            subtitle: {
                style: {
                    color: '#666666',
                    font: 'bold 12px "Trebuchet MS", Verdana, sans-serif'
                }
            },
            xAxis: {
                gridLineWidth: 1,
                lineColor: '#000',
                tickColor: '#000',
                labels: {
                    style: {
                        color: '#000',
                        font: '11px Trebuchet MS, Verdana, sans-serif'
                    }
                },
                title: {
                    style: {
                        color: '#333',
                        fontWeight: 'bold',
                        fontSize: '12px',
                        fontFamily: 'Trebuchet MS, Verdana, sans-serif'

                    }
                }
            },
            yAxis: {
                minorTickInterval: 'auto',
                lineColor: '#000',
                lineWidth: 1,
                tickWidth: 1,
                tickColor: '#000',
                labels: {
                    style: {
                        color: '#000',
                        font: '11px Trebuchet MS, Verdana, sans-serif'
                    }
                },
                title: {
                    style: {
                        color: '#333',
                        fontWeight: 'bold',
                        fontSize: '12px',
                        fontFamily: 'Trebuchet MS, Verdana, sans-serif'
                    }
                }
            },
            legend: {
                itemStyle: {
                    font: '9pt Trebuchet MS, Verdana, sans-serif',
                    color: 'black'

                },
                itemHoverStyle: {
                    color: '#039'
                },
                itemHiddenStyle: {
                    color: 'gray'
                }
            },
            labels: {
                style: {
                    color: '#99b'
                }
            },

            navigation: {
                buttonOptions: {
                    theme: {
                        stroke: '#CCCCCC'
                    }
                }
            }
        };

        // Apply the theme
        var highchartsOptions = Highcharts.setOptions(Highcharts.theme);
        $(function () {
            $('#container').highcharts({
                chart: {
                    zoomType: 'xy'
                },
                title: {
                    text: '股东人数统计表'
                },
                subtitle: {
                    text: '${code} ${name}'
                },
                xAxis: [{
                    categories: <%=objectMapper.writeValueAsString(categories)%>
                }],
                yAxis: [{ // Primary yAxis
                    labels: {
                        format: '{value}',
                        style: {
                            color: '#89A54E'
                        }
                    },
                    title: {
                        text: '价格',
                        style: {
                            color: '#89A54E'
                        }
                    }
                }, { // Secondary yAxis
                    title: {
                        text: '人数',
                        style: {
                            color: '#4572A7'
                        }
                    },
                    labels: {
                        format: '{value}人',
                        style: {
                            color: '#4572A7'
                        }
                    },
                    opposite: true
                }],
                tooltip: {
                    shared: true
                },
                legend: {
                    layout: 'vertical',
                    align: 'left',
                    x: 120,
                    verticalAlign: 'top',
                    y: 100,
                    floating: true,
                    backgroundColor: '#FFFFFF'
                },
                series: [{
                    name: '人数',
                    color: '#4572A7',
                    type: 'column',
                    yAxis: 1,
                    data: <%=objectMapper.writeValueAsString(counts)%>,
                    tooltip: {
                        valueSuffix: '人'
                    }

                }, {
                    name: '价格',
                    color: '#89A54E',
                    type: 'spline',
                    data: <%=objectMapper.writeValueAsString(prices)%>,
                    tooltip: {
                        valueSuffix: ''
                    }
                }]
            });
        });

    </script>

</head>
<%@ include file="../topBar.jsp"%>
<div id="bodyContainer">

    <div class="listview">
        <div id="container" style="min-width:700px;height:400px;"></div>
    </div>

    <div id="footer"><span>Powered By</span></div>
</div>


</html>