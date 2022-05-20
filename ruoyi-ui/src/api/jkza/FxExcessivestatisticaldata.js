import request from '@/utils/request'

// 查询扬尘数据列表
export function listFxExcessivestatisticaldata(query) {
  return request({
    url: '/jkza/FxExcessivestatisticaldata/list',
    method: 'get',
    params: query
  })
}

// 查询扬尘数据详细
export function getFxExcessivestatisticaldata(projectguid) {
  return request({
    url: '/jkza/FxExcessivestatisticaldata/' + projectguid,
    method: 'get'
  })
}

// 新增扬尘数据
export function addFxExcessivestatisticaldata(data) {
  return request({
    url: '/jkza/FxExcessivestatisticaldata',
    method: 'post',
    data: data
  })
}

// 修改扬尘数据
export function updateFxExcessivestatisticaldata(data) {
  return request({
    url: '/jkza/FxExcessivestatisticaldata',
    method: 'put',
    data: data
  })
}

// 删除扬尘数据
export function delFxExcessivestatisticaldata(projectguid) {
  return request({
    url: '/jkza/FxExcessivestatisticaldata/' + projectguid,
    method: 'delete'
  })
}
