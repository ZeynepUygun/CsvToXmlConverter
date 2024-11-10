<users>
<#list users as key, user>
    <user>
     <#list headers as header>
        <#if header == "role">
        <roles>
            <#list user.roles as role>
            <role>${role}</role>
            </#list>
        </roles>
        <#else>
        <${header}>${user[header]?default('empty or null')}</${header}>
        </#if>
     </#list>
    </user>
</#list>
</users>