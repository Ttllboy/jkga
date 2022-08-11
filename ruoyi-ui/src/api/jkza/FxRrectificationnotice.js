import request from '@/utils/request'

// 查询整改信息列表
export function listFxRrectificationnotice(query) {
  return request({
    url: '/jkza/FxRrectificationnotice/list',
    method: 'get',
    params: query
  })
}

// 查询整改信息详细
export function getFxRrectificationnotice(projectguid) {
  return request({
    url: '/jkza/FxRrectificationnotice/' + projectguid,
    method: 'get'
  })
}

// 新增整改信息
export function addFxRrectificationnotice(data) {
  return request({
    url: '/jkza/FxRrectificationnotice',
    method: 'post',
    data: data
  })
}

// 修改整改信息
export function updateFxRrectificationnotice(data) {
  return request({
    url: '/jkza/FxRrectificationnotice',
    method: 'put',
    data: data
  })
}

// 删除整改信息
export function delFxRrectificationnotice(projectguid) {
  return request({
    url: '/jkza/FxRrectificationnotice/' + projectguid,
    method: 'delete'
  })
}

//查询所有项目Id
export function listProjectId(){
  return request({
    url:'/jkza/building/listProjectId',
    method:'post'
  })
}
