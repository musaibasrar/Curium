<%-- 
    Document   : Enquiry Form - Saved Confirmation
    Created on : 2026-01-14
    Author     : Musaib
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.*" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
    <title>Enquiry Saved - Confirmation</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width,initial-scale=1">

    <style>
        :root{
            --success: #2b8a3e;
            --muted: #556a70;
            --card: #ffffff;
            --page-bg: linear-gradient(180deg, #eef7f8 0%, #ffffff 100%);
            --radius: 10px;
        }
        html,body{
            margin:0;
            padding:0;
            height:100%;
            font-family: "Segoe UI", Roboto, Tahoma, Arial, sans-serif;
            background: var(--page-bg);
            color:var(--muted);
        }
        .wrap{
            min-height:100vh;
            display:flex;
            align-items:center;
            justify-content:center;
            padding:30px;
            box-sizing:border-box;
        }
        .card{
            width:100%;
            max-width:760px;
            background:var(--card);
            border-radius:var(--radius);
            box-shadow:0 12px 30px rgba(0,0,0,0.08);
            padding:26px;
            box-sizing:border-box;
            text-align:center;
            border:1px solid rgba(0,0,0,0.04);
        }
        .logo img{
            height:70px;
            width:140px;
            object-fit:contain;
            margin-bottom:10px;
        }
        .status {
            display:flex;
            align-items:center;
            justify-content:center;
            gap:12px;
            margin:18px 0;
        }
        .status .icon {
            width:64px;
            height:64px;
            border-radius:50%;
            background:rgba(43,138,62,0.12);
            display:flex;
            align-items:center;
            justify-content:center;
            color:var(--success);
            font-size:32px;
            font-weight:700;
        }
        h1 {
            margin:6px 0 4px;
            color:var(--success);
            font-size:20px;
        }
        p.lead {
            margin:0 0 14px;
            color:var(--muted);
            font-size:14px;
        }
        .summary {
            text-align:left;
            max-width:640px;
            margin:14px auto;
            background:#fbfeff;
            border-radius:8px;
            padding:12px 14px;
            border:1px solid rgba(43,138,62,0.06);
            color:#214f53;
        }
        .summary dt { font-weight:700; margin-top:8px; }
        .summary dd { margin:0 0 8px 0; color:#234c4f; }
        .actions {
            margin-top:18px;
            display:flex;
            gap:12px;
            justify-content:center;
            flex-wrap:wrap;
        }
        .btn {
            display:inline-block;
            padding:9px 16px;
            border-radius:8px;
            text-decoration:none;
            color:white;
            background: linear-gradient(90deg,#2E6E7F,#4FB0C6);
            font-weight:600;
            box-shadow:0 6px 12px rgba(79,176,198,0.12);
        }
        .btn.secondary {
            background: #f3f7f8;
            color: #2b6b72;
            box-shadow:none;
            border:1px solid rgba(0,0,0,0.06);
        }
        .note {
            margin-top:12px;
            font-size:13px;
            color:#6b8a8d;
        }

        @media (max-width:520px){
            .summary { padding:10px; }
            .status .icon { width:56px; height:56px; font-size:28px; }
        }
    </style>
</head>
<body>
    <div class="wrap">
        <div class="card">
            <div class="logo">
                <img border="0" alt="logo" style="vertical-align: text-bottom;height: 110px;width: 95px;" src="/sky/images/sky.png">
            </div>

            <div class="status" role="status" aria-live="polite">
                <div class="icon">✓</div>
                <div>
                    <h1>Application Submitted Successfully</h1>
                    <p class="lead">Thank you — your enquiry has been saved.</p>
                </div>
            </div>
        </div>
    </div>

</body>
</html>