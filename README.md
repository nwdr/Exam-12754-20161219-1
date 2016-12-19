# Exam-12754-20161219-1
数据库用户名：root，密码为空
右键点击 run as > run configurations > Maven Build
       >右键点击new >base directory选择本项目运行  (goals:jetty:run)

运行后在浏览器地址栏输入localhost:8080即可进入index.jsp界面，有“登录”和“查看film”两个链接，
未登录则点击“查看film”还是会跳转到登录界面
登录成功后提示登陆成功，则可返回index.jsp页面点击链接“查看film”查看film信息
film.jsp页面有film信息点击“新增”链接跳转到addFilm.jsp新增film，新增成功返回film.jsp界面
               列表每一栏都有“删除”和“编辑”链接，
               点击“编辑”链接跳转到updateFilm.jsp修改film，修改成功返回film.jsp界面
               点击“删除”链接即可删除一条数据
