<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:include page="/WEB-INF/view/admin/nav.jsp"></jsp:include>
<script src=""></script>
<!-- Page Content -->
<div id="page-wrapper">

    <div class="container-fluid">
        <div>
            <h1 class="page-header">团队管理</h1>
        </div>
        <div class="row">
            <div class="col-lg-12">
                <div class="panel panel-default">
                    <div class="panel-heading">
                        团队管理
                    </div>
                    <!-- /.panel-heading -->
                    <div class="panel-body">
                        <div class="dataTable_wrapper">
                            <table class="table table-striped table-bordered table-hover" id="dataTables-example">

                                <thead>
                                <tr>
                                    <th>所属分公司</th>
                                    <th>所属部门</th>
                                    <th>团队名称</th>
                                    <th>操作</th>
                                </tr>
                                </thead>
                                <tbody>
                                <c:forEach var="clazz" items="${clazzList}">
                                    <tr>
                                        <td>${clazz.clazzId}</td>
                                        <td>${clazz.specName}</td>
                                        <td>${clazz.teamName}</td>
                                        <td>
                                            <a href="${pageContext.request.contextPath}/clazz.do/delete?clazzId=${clazz.clazzId}"
                                               onclick="return confirm('是否要删除该团队')">删除</a>
                                        </td>
                                    </tr>
                                </c:forEach>
                                </tbody>
                            </table>
                        </div>
                        <!-- /.table-responsive -->
                    </div>
                    <!-- /.panel-body -->
                </div>
                <!-- /.panel -->
            </div>
            <!-- /.col-lg-12 -->
        </div>

        <%--</c:forEach>--%>

    </div>
    <a href="${pageContext.request.contextPath}/clazz.do/clazz_add.view" class="btn btn-primary" role="button">添加团队</a>
    <!-- /.container-fluid -->
</div>
</div>
<!-- /#page-wrapper -->
</div>
<!-- /#wrapper -->

<jsp:include page="/bottom.jsp"></jsp:include>

