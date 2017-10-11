<template>
  <div id="treeDemo" >
    <div>
      <div>
        <h2>Default Tree</h2>
        <code>
          showCheckbox: false,
          halfCheck: false
        </code>
        <Tree0 :key="0"></Tree0>
      </div>
      <div>
        <h2>Default Tree with checkbox</h2>
        <code>
          showCheckbox: true,
          halfCheck: true
        </code>
        <Tree1 :key="1"></Tree1>
      </div>
    </div>
    <div>
      <div>
        <h2> Lazy Load Tree</h2>
        <Tree2 :key="2"></Tree2>

      </div>
      <div>
        <h2> Lazy Load Tree with checkbox </h2>
        <Tree3 :key="3"></Tree3>
      </div>
    </div>
   </div>
</template>
<script>
  import Tree0 from '../test/Tree0'
  import Tree1 from '../test/Tree1'
  import Tree2 from '../test/Tree2'
  import Tree3 from '../test/Tree3'

  export default {
    name: "treeDemo",
    data () {
      return {

      }
    },
    components: {
      Tree0,
      Tree1,
      Tree2,
      Tree3
    }

  }
</script>
<style>
  #treeDemo {
    margin: 40px 40px;

  }
  #treeDemo > div {
    display: flex;
    width: 100%;
    padding: 20px 20px;
    flex-shrink: 1;
    margin: 0 auto;
    justify-content: flex-start;
    align-content: flex-start;

  }
  #treeDemo > div  > div {
    flex: 1;
    padding: 20px 20px;
    margin: 0 auto;

  }
</style>

