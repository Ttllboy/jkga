import request from '@/utils/request'

// 查询工人考勤列表
export function listFxPersoninfo(query) {
  return request({
    url: '/jkza/FxPersoninfo/list',
    method: 'get',
    params: query
  })
}

// 查询工人考勤详细
export function getFxPersoninfo(projectguid) {
  return request({
    url: '/jkza/FxPersoninfo/' + projectguid,
    method: 'get'
  })
}

// 新增工人考勤
export function addFxPersoninfo(data) {
  return request({
    url: '/jkza/FxPersoninfo',
    method: 'post',
    data: data
  })
}

// 修改工人考勤
export function updateFxPersoninfo(data) {
  return request({
    url: '/jkza/FxPersoninfo',
    method: 'put',
    data: data
  })
}

// 删除工人考勤
export function delFxPersoninfo(projectguid) {
  return request({
    url: '/jkza/FxPersoninfo/' + projectguid,
    method: 'delete'
  })
}
