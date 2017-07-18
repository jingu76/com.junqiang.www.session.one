<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:include page="/WEB-INF/view/admin/nav.jsp"></jsp:include>
<!-- Page Content -->
<div id="page-wrapper">
    <div class="container-fluid">
        <div>
            <h1 class="page-header">分公司管理</h1>
        </div>
        <div class="panel-heading">
        </div>
        <div class="row">
            <div class="col-lg-12">
                <div class="panel panel-default">
                    <div class="panel-heading">
                        分公司信息
                    </div>
                    <!-- /.panel-heading -->
                    <div class="panel-body">
                        <div class="dataTable_wrapper">
                            <table class="table table-striped table-bordered table-hover" id="dataTables-example">
                                <thead>
                                <tr>
                                    <th width ="50%">分公司名称</th>
                                    <th width ="15%">负责人</th>
                                    <th width ="15%">审核人</th>
                                    <th width ="10%"></th>
                                    <th width ="10%"></th>
                                </tr>
                                </thead>
                                <tbody>
                                <c:forEach var="dept" items="${deptList}">
                                    <tr>
                                        <td>${dept.deptName}</td>
                                        <td></td>
                                        <td></td>
                                        <td>
                                            <a href="${pageContext.request.contextPath}/dept.do/dept_update.view?deptId=${dept.deptId}&deptName=${dept.deptName}">修改</a>
                                        </td>
                                        <td>
                                            <a href="${pageContext.request.contextPath}/dept.do/delete?deptId=${dept.deptId}"
                                               onclick="return confirm('是否要删除该分公司信息')">删除</a>
                                        </td>
                                    </tr>
                                </c:forEach>
                                </tbody>
                            </table>
                            <a href="${pageContext.request.contextPath}/dept.do/dept_add.view" class="btn btn-primary" role="button">添加分公司信息</a>
                        </div>
                        <!-- /.table-responsive -->
                    </div>
                    <!-- /.panel-body -->
                </div>
                <!-- /.panel -->
            </div>
            <!-- /.col-lg-12 -->
        </div>
    </div>

    <!-- /.container-fluid -->
</div>
</div>
<!-- /#page-wrapper -->
</div>
<!-- /#wrapper -->

<jsp:include page="/bottom.jsp"></jsp:include>

