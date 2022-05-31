package ${package};

<#list imports as import>
    import ${import};
</#list>

/**
* ${comment}
*/
public class ${simpleName} {
<#list fields as field>
    /**
    * ${field.comment}
    */
    private ${field.typeSimpleName} ${field.name};
</#list>
}