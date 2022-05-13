import request from '@/utils/request'

// 查询考勤缺失列表
export function listCheckLose(query) {
  return request({
    url: '/jkza/checkLose/list',
    method: 'get',
    params: query
  })
}

// 查询考勤缺失详细
export function getCheckLose(id) {
  return request({
    url: '/jkza/checkLose/' + id,
    method: 'get'
  })
}

// 新增考勤缺失
export function addCheckLose(data) {
  return request({
    url: '/jkza/checkLose',
    method: 'post',
    data: data
  })
}

// 修改考勤缺失
export function updateCheckLose(data) {
  return request({
    url: '/jkza/checkLose',
    method: 'put',
    data: data
  })
}

// 删除考勤缺失
export function delCheckLose(id) {
  return request({
    url: '/jkza/checkLose/' + id,
    method: 'delete'
  })
}
