# com.junqiang.www.session.one


Working of Way:

# IntelliJ IDEA 提交代码到 GitHub (http://blog.csdn.net/jeffleo/article/details/56017644)

一、设置相关绑定

Settings—>Version Control—>Git（提前下载好）—>Path to git executable—>选择你提前下载好的的 git.exe 安装目录
1 git exe

Settings ——Version Control——GitHub（Host：github.com，Login:账号，Password：密码 ）
2 github


二、配置远程

Share Project on GitHub（上传项目到GitHub） ：
VCS—>Import into Version Control—>Share Project on GitHub
share project on github

Clone 克隆 GitHub 上的代码到本地：
在 GitHub 上创建仓库后，复制仓库地址，比如 https://github.com/FatliTalk/test ，
在 IntelliJ IDEA 中 VCS—>Checkout from Version Control—>Git（或GitHub）中，粘贴仓库url地址（选择仓库），从 GitHub 仓库中 Clone 克隆一份项目，然后就可以在本地进行修改，然后再 Push 到 GitHub（见2.3）。
github

IntelliJ IDEA 上 Push 修改后的代码到 GitHub：
选中项目—>右键—>Git—>Repository—>Push（在 Push 到 GitHub 之前，需 Add—>Commit 到本地仓库）
PS：在实际开发过程中，我们还可以使用本地的 git（或者 GitHub 客户端）进行版本管理 ，最后使用push 进行远程提交。
push to github
