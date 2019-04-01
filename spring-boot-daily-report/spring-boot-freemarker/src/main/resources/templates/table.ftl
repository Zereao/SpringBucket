<html lang="en">
<head>
    <meta charset="UTF-8"/>
    <title>XX平台日报</title>
    <style>
        body {
            background: white;
        }

        #Main {
            width: 100%;
            text-align: center;
            margin: 0 auto;
        }

        table {
            background: black;
            width: 100%;
            border-collapse: collapse;
            text-align: left;
            cursor: default;
            border: 1px solid #ccc;
            margin: 20px 5px 5px 10px;
            -webkit-box-shadow: 0 0 8px rgba(0, 255, 255, 0.75);
            -moz-box-shadow: 0 0 8px rgba(0, 255, 255, 0.75);
            box-shadow: 0 0 8px rgba(0, 255, 255, 0.75);
        }

        .tbTitle > th {
            font-weight: 300;
            text-align: center;
            padding: 10px 0 10px 0;
            font: 15px "微软雅黑", Arial, Helvetica, sans-serif;
            background-color: rgba(0, 93, 93, 0.8);
            color: #efefef;
            text-shadow: 0 0 20px rgba(127, 255, 255, 1);
        }

        th, td {
            border: 1px solid rgba(127, 255, 255, 0.55);

        }

        .tbContext:hover {
            background-color: rgba(0, 99, 99, 0.9) !important;
        }

        td, td > a {
            font: 13px "微软雅黑", Arial, Helvetica, sans-serif;
            text-align: center;
            padding: 6px 0 6px 0;
            color: rgba(127, 255, 255, 0.75);
            text-shadow: 0 0 20px rgba(0, 255, 255, 0.75);
        }

        .tbContext:nth-child(2n+1) {
            background-color: rgba(0, 127, 127, 0.1);
        }
    </style>
</head>
<body>
<div id="table" style="height: 67px; max-height: 989px;">
    <div id="Main">
        <table>
            <thead>
            <tr class="tbTitle">
                <th>系统代号</th>
                <th>系统名称</th>
                <th>类型</th>
                <th>总量</th>
                <th>成功数</th>
                <th>失败数</th>
                <th>未知数</th>
                <th>延迟10S</th>
                <th>延迟10-30S</th>
                <th>延迟30-60S</th>
                <th>延迟1-10Min</th>
                <th>延迟>10Min</th>
            </tr>
            </thead>
            <tbody>
            <#list dailyReportVOList as vo>
                <#assign detailList=vo.detailList>
                <#assign detailSize=detailList?size>
                <#assign detail0=detailList[0]>
                <tr class="tbContext">
                    <td rowspan="${detailSize}">${vo.system}</td>
                    <td rowspan="${detailSize}">${vo.systemName}</td>
                    <td>${(detailSize >0) ? then(detail0.sentType , '')}</td>
                    <td>${(detailSize >0) ? then(detail0.sendTotal , '')}</td>
                    <td>${(detailSize >0) ? then(detail0.successNum , '')}</td>
                    <td>${(detailSize >0) ? then(detail0.failedNum , '' )}</td>
                    <td>${(detailSize >0) ? then(detail0.unknownNum , '')}</td>
                    <td>${(detailSize >0) ? then(detail0.delay10Sec , '')}</td>
                    <td>${(detailSize >0) ? then(detail0.delay30Sec , '')}</td>
                    <td>${(detailSize >0) ? then(detail0.delay60Sec , '')}</td>
                    <td>${(detailSize >0) ? then(detail0.delay10Min , '')}</td>
                    <td>${(detailSize >0) ? then(detail0.delayOver10Min ,'')}</td>
                </tr>

                <#list detailList as detail>
                    <#assign index=detail_index>
                    <#if index!=0>
                        <tr class="tbContext">
                            <td>${detail.sentType}</td>
                            <td>${detail.sendTotal}</td>
                            <td>${detail.successNum}</td>
                            <td>${detail.failedNum}</td>
                            <td>${detail.unknownNum}</td>
                            <td>${detail.delay10Sec}</td>
                            <td>${detail.delay30Sec}</td>
                            <td>${detail.delay60Sec}</td>
                            <td>${detail.delay10Min}</td>
                            <td>${detail.delayOver10Min}</td>
                        </tr>
                    </#if>
                </#list>
            </#list>
            </tbody>
        </table>
    </div>
</div>
</body>
</html>