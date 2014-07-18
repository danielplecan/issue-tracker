<%-- 
    Document   : header
    Created on : Jul 17, 2014, 10:17:16 AM
    Author     : iapavaloaie
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<div class="navbar navbar-default navbar-fixed-top">
    <div class="container">
  <div class="navbar-header">
            <a class="navbar-brand" href="../">Issue Tracker</a>
            <button data-target="#navbar-main" data-toggle="collapse" type="button" class="navbar-toggle">
      <span class="icon-bar"></span>
      <span class="icon-bar"></span>
      <span class="icon-bar"></span>
    </button>
  </div>
        <div id="navbar-main" class="navbar-collapse collapse">
    <ul class="nav navbar-nav">
                
                <li>
                    <a href="../help/">About</a>
                </li>
                <li>
                    <a href="http://news.bootswatch.com">Create an issue</a>
                </li>
      
                <form class="navbar-form navbar-left">
                    <input type="text" placeholder="Search" class="form-control col-lg-8">
                </form>
    </ul>

            <ul class="nav navbar-nav navbar-right">
                <li class="dropdown">
                    <a id="themes" href="#" data-toggle="dropdown" class="dropdown-toggle">username<span class="caret"></span></a>
                    <ul aria-labelledby="themes" class="dropdown-menu">
                        <li><a href="../#">Profile</a></li>
                        <li><a href="../#">My issues</a></li>
                        <li><a href="../#">Assigned Issues</a></li>
                        <li class="divider"></li>
                        
                        <li><a href="../#">Log out</a></li>
    </ul>
                </li>
                
            </ul>

  </div>
</div>
</div>
